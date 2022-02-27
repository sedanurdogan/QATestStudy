Feature: Sign in to application

As a user
I want to sign in to "Hepsi Burada" with valid email and password
So that I can navigate to Home Page

Scenario: 001 Sign in with invalid email
Given I navigate to "https://hepsiburada.com" page
When I click "Giris Yap" button and  I directed to LoginPage
And I enter "abc@gmail" to email textbox
And I click "Giris Yap" button
Then I should see validation text "Geçerli bir e-posta adresi girmelisiniz"

Scenario: 002 Sign in with invalid phone number
Given I navigate to "https://hepsiburada.com" page
When I click "Giris Yap" button and  I directed to LoginPage
And I enter "565656" to email textbox
And I click Giris Yap button
Then I should see validation text "Geçerli bir cep telefonu girmelisiniz"

Scenario: 003 Sign in with valid email and invalid password
Given I navigate to "https://hepsiburada.com" page
When I click "Giris Yap" button and  I directed to LoginPage
And I enter "abc@gmail.com" to email textbox
And I click Giris Yap button
And I enter "invalidpassword" to password textbox
And I click Giris Yap button
Then I should see validation text "Girdiginiz sifre eksik veya hatali.Kontrol edip tekrar deneyin."

Scenario: 004 Sign in with valid email and password
Given I navigate to "https://hepsiburada.com" page
When I click "Giris Yap" button and  I directed to LoginPage
And I enter "tester@gmail.com" to email textbox
And I enter "abc12345" to password textbox
And I click Sign In button
Then I should see "Home Page" heading
And I should see "Hesabim" button 
But I should not see "Giris Yap" button 

As a user
I want to sign up to "Hepsi Burada" with valid user information 
So that I can login and navigate to Home Page

Scenario: 001 Sign up with invalid email
Given I navigate to "https://hepsiburada.com" page
When I click "Üye Ol" button and  I directed to LoginPage
And I enter "abc@gmail" to email textbox
And I press Tab button
Then I should see validation text "Geçerli bir e-posta adresi girmelisiniz" 
And I should see "Devam Et" button as disable


Scenario: 002 Sign up with valid email but email aldready sign in
Given I navigate to "https://hepsiburada.com" page
When I click "Üye Ol" button and  I directed to LoginPage
And I enter "abc@gmail.com" to email textbox
And I press "Devam Et" button
Then I should see validation text "Bu e-posta adresine ait bir hesabiniz oldugunu fark ettik." 


Scenario: 003 Sign up with valid email address
Given I navigate to "https://hepsiburada.com" page
When I click "Üye Ol" button and  I directed to LoginPage
And I enter "newmail@gmail.com" to email textbox
And I press "Devam Et" button
Then I should see validation text "Dogrulama maili gönderildi" 
And I should see  validation mail in mailbox

Scenario: 004 Sign up with missing information
Given I entered valid email to login page
And  The link in validation mail is exist 
When I click "validation Link" button and  I directed to LoginPage
And I enter "username" to name textbox
And I enter "1Abcdefth" to password textbox
And I select chechbox "Riza Metni"
And I press "Üye Ol" button
Then I should see validation text "Adinizi girmelisiniz" 

Scenario: 005 Sign up with invalid password 
Given I entered valid email to login page
And  The link in validation mail is exist 
When I click "validation Link" button and  I directed to LoginPage
And I enter "username" to name textbox
And I enter "surname" to surname textbox
And I enter "abcdsedf" to password textbox
And I select chechbox "Riza Metni"
And I press "Üye Ol" button
Then I should see "Üye Ol" button disable 
And I should see some criteria does not meet in password tooltip
And I shouldn't directed to Home Page 


Scenario: 006 Sign up with valid information
Given I entered valid email to login page
And  The link in validation mail is exist 
When I click "validation Link" button and  I directed to LoginPage
And I enter "username" to name textbox
And I enter "surname" to surname textbox
And I enter "1Abcdefth" to password textbox
And I select chechbox "Riza Metni"
And I press "Üye Ol" button
Then I should directed to Home Page 