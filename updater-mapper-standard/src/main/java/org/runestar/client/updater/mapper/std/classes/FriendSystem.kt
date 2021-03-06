package org.runestar.client.updater.mapper.std.classes

import org.kxtra.lang.list.startsWith
import org.objectweb.asm.Type.VOID_TYPE
import org.runestar.client.updater.mapper.IdentityMapper
import org.runestar.client.updater.mapper.annotations.DependsOn
import org.runestar.client.updater.mapper.annotations.MethodParameters
import org.runestar.client.updater.mapper.annotations.SinceVersion
import org.runestar.client.updater.mapper.extensions.and
import org.runestar.client.updater.mapper.extensions.predicateOf
import org.runestar.client.updater.mapper.extensions.type
import org.runestar.client.updater.mapper.tree.Class2
import org.runestar.client.updater.mapper.tree.Field2
import org.runestar.client.updater.mapper.tree.Method2

@SinceVersion(164)
@DependsOn(LoginType::class)
class FriendSystem : IdentityMapper.Class() {

    override val predicate = predicateOf<Class2> { it.superType == Any::class.type }
            .and { it.instanceFields.any { it.type == type<LoginType>() } }

    class loginType : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<Field2> { it.type == type<LoginType>() }
    }

    @DependsOn(FriendsList::class)
    class friendsList : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<Field2> { it.type == type<FriendsList>() }
    }

    @DependsOn(IgnoreList::class)
    class ignoreList : IdentityMapper.InstanceField() {
        override val predicate = predicateOf<Field2> { it.type == type<IgnoreList>() }
    }

    @MethodParameters()
    @DependsOn(UserList.clear::class, FriendsList::class)
    class clear : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<Method2> { it.returnType == VOID_TYPE }
                .and { it.instructions.any { it.isMethod && it.methodOwner == type<FriendsList>() && it.methodType == method<UserList.clear>().type } }
    }

    @DependsOn(ignoreList::class)
    @MethodParameters("name")
    class removeIgnore : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<Method2> { it.returnType == VOID_TYPE }
                .and { it.arguments.startsWith(String::class.type) }
                .and { it.instructions.any { it.isField && it.fieldId == field<ignoreList>().id } }
    }

    @DependsOn(friendsList::class)
    @MethodParameters("name")
    class removeFriend : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<Method2> { it.returnType == VOID_TYPE }
                .and { it.arguments.startsWith(String::class.type) }
                .and { it.instructions.any { it.isField && it.fieldId == field<friendsList>().id } }
    }
}