package api.suites

import api.regres.*
import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite

@Suite
@SelectClasses(
    SingleUserPage::class,
    SingleUserNotFoundPage::class,
    LoginPage::class,
    UserListPage::class,
    SingleResourcePage::class,
    SingleResourceNotFoundPage::class
)
class ApiSuite