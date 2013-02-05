package lb.datamagus.ui;

/**
 * Application starter.
 * Creates an instance of the {@link DataMagusApplication} class and starts it live cycle.
 * ASK how to do the same in Kotlin?
 */
public class DataMagusStarter
{
    public static void main(final String[] args)
    {
        javafx.application.Application.launch(DataMagusApplication.class, args);
    }
}
