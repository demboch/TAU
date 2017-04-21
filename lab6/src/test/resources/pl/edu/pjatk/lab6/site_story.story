Scenario: User is trying to login with wrong login and password
Given user is on Login page
When user inserts login: ZLY_LOGIN in input with name: Email
Then user inserts password: ZLE_HASLO in input with name: Password
When user clicks button with class name: btn-default
Then site displays failed login text in span with button: Zaloguj

Scenario: User is trying to login with correct login and password
Given user is on Login page
When user inserts login: Marian@wp.pl in input with name: Email
Then user inserts password: P@ssw0rd in input with name: Password
When user clicks button with class name: btn-default
Then site visible button Wyloguj in right upper corner after trying to log

Scenario: User is checking if his sessios is saved on site
Given user is on Home page
Then site contains link with text: Wyloguj
Then user clicks on link with text: GunShop

Scenario: User is checking if his session is destroyed after logout
Given user is on Home page
Then user clicks on link with text: Wyloguj
Then site contains link with text: Zaloguj