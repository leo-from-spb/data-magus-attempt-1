package lb.datamagus.model.core;


import lb.datamagus.model.concept.*;



/**
 * Meta-data initializer.
 **/
public final class Static
{

    //// REGISTRY \\\\

    public static final MetaRegistry registry;


    // static initializer
    static
    {
        registry = new MetaRegistry();
        registry.registerNodeDescriptors(new Class[]{Bone.class, ProjectRoot.class});
        registry.registerNodeDescriptors(new Class[]{Conceptual.class, SubArea.class, Domain.class});
        registry.registerNodeDescriptors(new Class[]{Entity.class, Attribute.class, Key.class});
    }

}
