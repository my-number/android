package dev.mynumber.connect.android.reader

import android.nfc.tech.IsoDep
import android.nfc.tech.NfcB
import android.nfc.tech.TagTechnology
import android.util.Log
import kotlin.experimental.and

class ApduParser(val tagtech: IsoDep) {
    init {
        tagtech.connect()
    }

    fun transmit(data: ByteArray): ByteArray {
        return tagtech.transceive(data)
    }

    fun transmitChecked(data: ByteArray): ByteArray {
        val apdu = transmit(data)
        val len = apdu.size
        if (len < 2) {
            throw Error("No response")
        }
        val sw1 = apdu[len - 2]
        val sw2 = apdu[len - 1]

        if ((sw1 == 0x90.toByte() || sw1 == 0x91.toByte()) && sw2 == 0x00.toByte()) {

            return apdu.slice(0..(len - 2)).toByteArray()
        }
        throw Error("Command failed %x %x".format(sw1, sw2))
    }
    fun selectDf(dfid: ByteArray) {
        val apdu = makeApdu(0x00, 0xa4.toByte(), 0x04.toByte(), 0x0c.toByte(), dfid, null)
        transmitChecked(apdu)
    }

    fun selectEf(efid: ByteArray) {
        val apdu = makeApdu(0x00, 0xa4.toByte(), 0x02.toByte(), 0x0c.toByte(), efid, null)
        transmitChecked(apdu)
    }

    fun getChallenge(size: Byte): ByteArray {
        val apdu = makeApdu(0x00, 0x84.toByte(), 0.toByte(), 0.toByte(), ByteArray(0), size)
        return transmitChecked(apdu)
    }

    enum class KeyType {
        UserAuth
    }

    fun verifyPin(pin: String, keyType: KeyType) {
        val apdu =
            makeApdu(0x00, 0x20.toByte(), 0x00.toByte(), 0x80.toByte(), pin.toByteArray(), null)
        transmitChecked(apdu)

    }

    fun lookupPin(keyType: KeyType): Byte {
        val apdu =
            makeApdu(0x00, 0x20.toByte(), 0x00.toByte(), 0x80.toByte(), ByteArray(0), null)
        val result = transmit(apdu)
        val len = result.size
        val sw1 = result[len - 2]
        val sw2 = result[len - 1]
        if (sw1 == 0x63.toByte() && (sw2 and 0xF0.toByte() == 0xC0.toByte())) {
            return sw2 and 0x0F
        } else {
            throw Error("Command failed %f %f".format(sw1, sw2))
        }
    }
    fun computeDigitalSignature(hashPkcs1: ByteArray): ByteArray {
        val apdu = makeApdu(0x80.toByte(), 0x2a.toByte(), 0x00.toByte(), 0x80.toByte(), hashPkcs1, 0)
        return transmit(apdu)
    }
    fun readBinary(length: Int): ByteArray{
        var data = ByteArray(0)
        while(true) {
            val cursize = data.size

        }
    }
    companion object {
        fun makeApdu(
            cla: Byte,
            ins: Byte,
            param1: Byte,
            param2: Byte,
            data: ByteArray,
            maxSize: Byte?
        ): ByteArray {
            var packetSize = 5;
            val dataSize = data.size
            if (dataSize == 0) {
                packetSize += 0
            } else if (dataSize <= 0xFF) {
                packetSize += 1 + dataSize
            } else if (dataSize <= 0xFFFF) {
                packetSize += 3 + dataSize
            } else {
                throw Error("Data size is too large")
            }
            var buf = ByteArray(packetSize)
            buf[0] = cla
            buf[1] = ins
            buf[2] = param1
            buf[3] = param2

            var dataStart: Int = 0;
            if (dataSize == 0) {
                dataStart = 4
            } else if (dataSize <= 0xff) {
                buf[4] = dataSize.toByte()
                dataStart = 5
            } else if (dataSize <= 0xffff) {
                buf[4] = 0
                buf[5] = (dataSize ushr 8).toByte()
                buf[6] = (dataSize and 0xff).toByte()
                dataStart = 7
            }
            for (i in 0 until dataSize) {
                buf[dataStart + i] = data[i]
            }
            if (maxSize != null) {
                buf[dataStart + dataSize] = maxSize
            }
            return buf
        }
    }
}