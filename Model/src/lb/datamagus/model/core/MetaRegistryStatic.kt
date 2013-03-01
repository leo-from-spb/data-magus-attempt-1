package lb.datamagus.model.core

import lb.datamagus.model.concept.*


val registry: MetaRegistry = MetaRegistry()


val zzz = array(

        registry.registerNodeDescriptor(javaClass<Bone>()),
        registry.registerNodeDescriptor(javaClass<ProjectRoot>()),
        registry.registerNodeDescriptor(javaClass<Conceptual>()),
        registry.registerNodeDescriptor(javaClass<SubArea>()),
        registry.registerNodeDescriptor(javaClass<Domain>()),
        registry.registerNodeDescriptor(javaClass<Entity>()),
        registry.registerNodeDescriptor(javaClass<Attribute>()),
        registry.registerNodeDescriptor(javaClass<Key>())

)
