package lb.datamagus.model.core;




/**
 * Meta-data initializer.
 **/
public final class MetaInitializer
{

    public static final MetaRegistry registry =
            new MetaRegistry();

    // static initializer
    static
    {
        registry.registerNodeDescriptors(new Class[] {Bone.class, ProjectRoot.class});
    }

}
