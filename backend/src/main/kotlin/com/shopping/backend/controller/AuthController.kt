package com.shopping.backend.controller

import com.shopping.backend.constants.APIName
import com.shopping.backend.constants.Constant
import com.shopping.backend.data_model.User
import com.shopping.backend.data_model.UserAddress
import com.shopping.backend.exception.ApplicationException
import com.shopping.backend.model.request.UserRequestModel
import com.shopping.backend.model.response.APIResponse
import com.shopping.backend.model.response.ResponseUtil
import com.shopping.backend.service.AuthService
import com.shopping.backend.service.UserAddressService
import com.shopping.backend.service.UserService
import com.shopping.backend.service.UserTokenService
import com.shopping.backend.util.APIStatus
import com.shopping.backend.util.MD5Hash
import com.shopping.backend.util.UniqueID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.regex.Pattern

@RestController
@RequestMapping(APIName.USERS)
class AuthController {
    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val userTokenService: UserTokenService? = null

    @Autowired
    private val userAddressService: UserAddressService? = null

    @Autowired
    private val authService: AuthService? = null

    @Autowired
    protected var responseUtil: ResponseUtil? = null

    @RequestMapping(path = [APIName.USER_REGISTER], method = [RequestMethod.POST], produces = [APIName.CHARSET])
    fun registerUser(@RequestBody user: UserRequestModel): ResponseEntity<APIResponse<*>> {
        // check user already exists
        val existedUser = userService!!.getUserByEmail(user.getEmail(), Constant.STATUS.ACTIVE_STATUS.value)
        return if (existedUser == null) {
            // email is valid to create user
            val email = user.getEmail()
            if (email != null && email != "") {
                val pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
                val matcher = pattern.matcher(email)
                if (!matcher.matches()) {
                    throw ApplicationException(APIStatus.ERR_INVALID_DATA)
                }
                val userSignUp = User()
                userSignUp.userId = UniqueID.getUUID()
                userSignUp.createDate = Date()
                userSignUp.email = email
                userSignUp.name = user.getName()
                userSignUp.salt = UniqueID.getUUID()
                try {
//                    String generatedString = RandomStringUtils.randomAlphabetic(6);
                    val generatedString = "123456"
                    val password = MD5Hash.MD5Encrypt(generatedString)
                    userSignUp.passwordHash = MD5Hash.MD5Encrypt(password + userSignUp.salt)
                } catch (ex: NoSuchAlgorithmException) {
                    throw RuntimeException("Encrypt user password error", ex)
                }
                userSignUp.status = Constant.USER_STATUS.ACTIVE.status
                userService.save(userSignUp)
                val userAddress = UserAddress()
                userAddress.userId = userSignUp.userId
                userAddress.adress = user.getAddress()
                userAddress.status = Constant.STATUS.ACTIVE_STATUS.value
                userAddressService!!.save(userAddress)
                // do send mail notify...
                responseUtil!!.successResponse(userSignUp)
            } else {
                throw ApplicationException(APIStatus.ERR_INVALID_DATA)
            }
        } else {
            // notify user already exists
            throw ApplicationException(APIStatus.USER_ALREADY_EXIST)
        }
    }
}