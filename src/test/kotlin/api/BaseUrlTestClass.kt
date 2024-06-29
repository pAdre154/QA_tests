package api

import org.junit.platform.commons.logging.Logger

abstract class BaseUrlTestClass {
    protected abstract val url: String
    protected abstract val logger: Logger
}
