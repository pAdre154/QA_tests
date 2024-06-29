package api.suites

import api.regres.UserDataTests
import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite

@Suite
@SelectClasses(
    UserDataTests::class,
)
class ApiSuite