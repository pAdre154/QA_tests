package api.suites

import api.regres.LoginPage
import api.regres.SingleUserNotFoundPage
import api.regres.SingleUserPage
import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite

@Suite
@SelectClasses(
    SingleUserPage::class,
    SingleUserNotFoundPage::class,
    LoginPage::class
)
class ApiSuite