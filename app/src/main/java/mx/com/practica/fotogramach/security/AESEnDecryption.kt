package mx.com.practica.fotogramach.security

import com.loopj.android.http.Base64
import java.security.MessageDigest
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESEnDecryption {

    @Throws(Exception::class)
    fun encrypt(ivStr: String, keyStr: String, bytes: ByteArray?): ByteArray? {
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        md.update(ivStr.toByteArray())
        val ivBytes: ByteArray = md.digest()
        val sha: MessageDigest = MessageDigest.getInstance("SHA-256")
        sha.update(keyStr.toByteArray())
        val keyBytes: ByteArray = sha.digest()
        return encrypt(ivBytes, keyBytes, bytes)
    }

    @Throws(Exception::class)
    fun encrypt(ivBytes: ByteArray?, keyBytes: ByteArray?, bytes: ByteArray?): ByteArray? {
        val ivSpec: AlgorithmParameterSpec = IvParameterSpec(ivBytes)
        val newKey = SecretKeySpec(keyBytes, "AES")
        val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec)
        return cipher.doFinal(bytes)
    }

    @Throws(Exception::class)
    fun decrypt(ivStr: String, keyStr: String, bytes: ByteArray?): ByteArray? {
        val md: MessageDigest = MessageDigest.getInstance("MD5")
        md.update(ivStr.toByteArray())
        val ivBytes: ByteArray = md.digest()
        val sha: MessageDigest = MessageDigest.getInstance("SHA-256")
        sha.update(keyStr.toByteArray())
        val keyBytes: ByteArray = sha.digest()
        return decrypt(ivBytes, keyBytes, bytes)
    }

    @Throws(Exception::class)
    fun decrypt(ivBytes: ByteArray?, keyBytes: ByteArray?, bytes: ByteArray?): ByteArray? {
        val ivSpec: AlgorithmParameterSpec = IvParameterSpec(ivBytes)
        val newKey = SecretKeySpec(keyBytes, "AES")
        val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec)
        return cipher.doFinal(bytes)
    }

    @Throws(java.lang.Exception::class)
    fun encryptStrAndToBase64(ivStr: String?, keyStr: String?, enStr: String): String? {
        val bytes = encrypt(keyStr!!, keyStr, enStr.toByteArray(charset("UTF-8")))
        return String(Base64.encode(bytes, Base64.DEFAULT), charset("UTF-8"))
    }

    @Throws(java.lang.Exception::class)
    fun decryptStrAndFromBase64(ivStr: String?, keyStr: String?, deStr: String): String? {
        val bytes = decrypt(
            keyStr!!,
            keyStr, Base64.decode(deStr.toByteArray(charset("UTF-8")), Base64.DEFAULT)
        )
        return String(bytes!!, charset("UTF-8"))
    }
}