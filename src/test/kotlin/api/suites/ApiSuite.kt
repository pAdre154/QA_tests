package api.suites

import api.regres.LoginPage
import api.regres.SingleUserNotFoundPage
import api.regres.SingleUserPage
import api.regres.UserListPage
import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite

@Suite
@SelectClasses(
    SingleUserPage::class,
    SingleUserNotFoundPage::class,
    LoginPage::class,
    UserListPage::class,
)
class ApiSuite