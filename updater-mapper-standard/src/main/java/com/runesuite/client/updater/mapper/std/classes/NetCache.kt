package com.runesuite.client.updater.mapper.std.classes

import com.runesuite.mapper.IdentityMapper
import com.runesuite.mapper.annotations.DependsOn
import com.runesuite.mapper.extensions.Predicate
import com.runesuite.mapper.extensions.predicateOf
import com.runesuite.mapper.tree.Class2

@DependsOn(Client.NetCache_indexCaches::class)
class NetCache : IdentityMapper.Class() {
    override val predicate = predicateOf<Class2> { field<Client.NetCache_indexCaches>().klass == it }
}