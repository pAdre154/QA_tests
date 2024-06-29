package api

import org.junit.platform.commons.logging.Logger

abstract class BaseUrlTest {
    protected abstract val url: String
    protected abstract val logger: Logger
}