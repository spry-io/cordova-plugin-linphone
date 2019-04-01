# cordova-plugin-linphone

Plugin aims sip development from liblinphone. Meanwhile, it only registers.

<h2>SIP plugin for Cordova & Phonegap Apps (Android only)</h2>

## Installation

Cordova local build

    cordova plugin add https://github.com/spry-io/cordova-plugin-linphone.git

## Usage

```
    var sipManager = {
        register: function () {
            cordova.plugins.sip.login('203', '203', '192.168.1.111:5060', function (e) {

                if (e == 'RegistrationSuccess') {
                    console.log(e);
                    sipManager.listen();
                } else {
                    alert("Registration Failed!");
                }

            }, function (e) { console.log(e) })
        }
    }
```
