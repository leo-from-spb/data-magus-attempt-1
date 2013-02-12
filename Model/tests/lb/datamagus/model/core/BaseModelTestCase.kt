package lb.datamagus.model.core

import lb.testutils.*
import org.testng.annotations.*

public abstract class BaseModelTestCase
{

    private val realModel = RealDataMagusModel();
    protected val model: WorkModel = realModel;


    [BeforeClass]
    protected open fun beforeClass()
    {
        Static.registry.registerNodeDescriptor(javaClass<DumbTestBone>())
    }


    [BeforeMethod]
    protected open fun beforeMethod()
    {
        realModel.countNodes _equals_ 0;
    }


    [AfterMethod]
    protected open fun afterMethod()
    {
        realModel.modify("Cleanup model after tests") {
            it.dropAllNodes()
        }
    }


}