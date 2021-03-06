package org.runestar.client.updater.mapper.std.classes

import org.runestar.client.updater.mapper.IdentityMapper
import org.runestar.client.updater.mapper.OrderMapper
import org.runestar.client.updater.mapper.annotations.DependsOn
import org.runestar.client.updater.mapper.extensions.Predicate
import org.runestar.client.updater.mapper.extensions.and
import org.runestar.client.updater.mapper.extensions.predicateOf
import org.runestar.client.updater.mapper.extensions.type
import org.runestar.client.updater.mapper.tree.Class2
import org.runestar.client.updater.mapper.tree.Field2
import org.runestar.client.updater.mapper.tree.Instruction2
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.Type.*
import java.lang.reflect.Modifier

class Task : IdentityMapper.Class() {
    override val predicate = predicateOf<Class2> { it.superType == Any::class.type }
            .and { it.instanceFields.any { Modifier.isVolatile(it.access) && it.type == Any::class.type } }

    class result : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<Field2> { Modifier.isVolatile(it.access) && it.type == Any::class.type }
    }

    class objectArgument : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<Field2> { !Modifier.isVolatile(it.access) && it.type == Any::class.type }
    }

    class status : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<Field2> { Modifier.isVolatile(it.access) && it.type == INT_TYPE }
    }

    class next : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<Field2> { it.type == type<Task>() }
    }

    @DependsOn(TaskHandler.newTask::class)
    class type : OrderMapper.InMethod.Field(TaskHandler.newTask::class, 0) {
        override val predicate = predicateOf<Instruction2> { it.opcode == PUTFIELD }
    }

    @DependsOn(TaskHandler.newTask::class)
    class intArgument : OrderMapper.InMethod.Field(TaskHandler.newTask::class, 1) {
        override val predicate = predicateOf<Instruction2> { it.opcode == PUTFIELD }
    }
}