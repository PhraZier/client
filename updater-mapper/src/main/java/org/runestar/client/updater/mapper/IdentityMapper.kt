package org.runestar.client.updater.mapper

import org.runestar.client.updater.mapper.tree.Class2
import org.runestar.client.updater.mapper.tree.Field2
import org.runestar.client.updater.mapper.tree.Jar2
import org.runestar.client.updater.mapper.tree.Method2
import org.runestar.client.updater.mapper.extensions.Predicate
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

abstract class IdentityMapper<T> : Mapper<T>() {

    override fun match(jar: Jar2): T {
        val matches = options(jar).filter { predicate(it) }.toList()
        check(matches.isNotEmpty()) { "$this: No matches" }
        check(matches.size == 1) { "$this: Multiple matches: $matches" }
        return matches.first()
    }

    protected abstract fun options(jar: Jar2): Sequence<T>

    protected abstract val predicate: Predicate<T>

    abstract class Class : IdentityMapper<Class2>(), ElementMatcher.Class {
        override fun options(jar: Jar2): Sequence<Class2> {
            return jar.classes.asSequence()
        }
    }

    abstract class Field : IdentityMapper<Field2>(), ElementMatcher.Field {
        override fun options(jar: Jar2): Sequence<Field2> {
            return jar.classes.asSequence().flatMap { it.fields.asSequence() }
        }
    }

    abstract class Method : IdentityMapper<Method2>(), ElementMatcher.Method {
        override fun options(jar: Jar2): Sequence<Method2> {
            return jar.classes.asSequence().flatMap { it.methods.asSequence() }
        }
    }

    abstract class StaticField : Field() {
        override fun options(jar: Jar2): Sequence<Field2> {
            return super.options(jar).filter { Modifier.isStatic(it.access) }
        }
    }

    abstract class InstanceField() : Field() {

        @Suppress("UNCHECKED_CAST")
        private val enclosing = javaClass.enclosingClass.kotlin as KClass<out Mapper<Class2>>

        override fun options(jar: Jar2): Sequence<Field2> {
            return super.options(jar).filter { context.classes[enclosing] == it.klass && !Modifier.isStatic(it.access)}
        }
    }

    abstract class StaticMethod() : Method() {
        override fun options(jar: Jar2): Sequence<Method2> {
            return super.options(jar).filter { Modifier.isStatic(it.access) && !it.isClassInitializer }
        }
    }

    abstract class InstanceMethod() : Method() {

        @Suppress("UNCHECKED_CAST")
        private val enclosing = javaClass.enclosingClass.kotlin as KClass<out Mapper<Class2>>

        override fun options(jar: Jar2): Sequence<Method2> {
            return super.options(jar).filter { context.classes[enclosing] == it.klass && !Modifier.isStatic(it.access) && !it.isConstructor }
        }
    }
}