package org.runestar.client.updater.mapper.std.classes

import org.runestar.client.updater.mapper.IdentityMapper
import org.runestar.client.updater.mapper.annotations.DependsOn
import org.runestar.client.updater.mapper.annotations.SinceVersion
import org.runestar.client.updater.mapper.extensions.predicateOf
import org.runestar.client.updater.mapper.tree.Class2

@SinceVersion(141)
@DependsOn(Client.ViewportMouse_isInViewport::class)
class ViewportMouse : IdentityMapper.Class() {

    override val predicate = predicateOf<Class2> { field<Client.ViewportMouse_isInViewport>().klass == it }
}