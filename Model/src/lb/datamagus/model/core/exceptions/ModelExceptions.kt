package lb.datamagus.model.core.exceptions;

public open class ModelException (message: String) : Exception (message) {}

public class NoSuchNodeException (message: String) : ModelException (message) {}

public class NodeClassMismatchException (message: String) : ModelException (message) {}

public class NoReferencesException (message: String) : ModelException(message) {}

public class AlienNodeException (message: String) : ModelException(message) {}


