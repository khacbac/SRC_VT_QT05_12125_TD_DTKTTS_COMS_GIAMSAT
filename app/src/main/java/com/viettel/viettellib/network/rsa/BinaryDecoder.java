/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.viettellib.network.rsa;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: TruongHN
 *  @version: 1.0
 *  @since: Feb 2, 2012
 */
public interface BinaryDecoder extends Decoder {
	byte[] decode(byte[] pArray) throws DecoderException;
}
