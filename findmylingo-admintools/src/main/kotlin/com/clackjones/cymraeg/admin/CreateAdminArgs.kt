package com.clackjones.cymraeg.admin

import com.beust.jcommander.Parameter;

class CreateAdminArgs {
    @Parameter(names = arrayOf("-n", "--nickname"), description = "The name seen by other users", required = true)
    var nickname: String? = null
    @Parameter(names = arrayOf("-u", "--user"), description = "The email address used as username and password reset", required = true)
    var username: String? = null
    @Parameter(names = arrayOf("-p", "--password"), description = "The password used to log in", password = true, required = true)
    var password: String? = null
}
