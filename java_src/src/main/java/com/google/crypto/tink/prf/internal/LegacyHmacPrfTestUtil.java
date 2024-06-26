// Copyright 2023 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
////////////////////////////////////////////////////////////////////////////////

package com.google.crypto.tink.prf.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.prf.HmacPrfKey;
import com.google.crypto.tink.prf.HmacPrfParameters;
import com.google.crypto.tink.prf.HmacPrfParameters.HashType;
import com.google.crypto.tink.subtle.Hex;
import com.google.crypto.tink.util.SecretBytes;
import java.security.GeneralSecurityException;

@AccessesPartialKey
final class LegacyHmacPrfTestUtil {
  static class HmacLegacyPrfTestVector {
    HmacPrfKey key;
    public byte[] message;
    public byte[] tag;

    public HmacLegacyPrfTestVector(HashType hashType, String key, String message, String tag) {
      try {
        this.key =
            HmacPrfKey.builder()
                .setKeyBytes(SecretBytes.copyFrom(Hex.decode(key), InsecureSecretKeyAccess.get()))
                .setParameters(
                    HmacPrfParameters.builder()
                        .setKeySizeBytes(Hex.decode(key).length)
                        .setHashType(hashType)
                        .build())
                .build();
      } catch (GeneralSecurityException e) {
        throw new IllegalStateException(e);
      }
      this.message = Hex.decode(message);
      this.tag = Hex.decode(tag);
    }
  }

  // Test data from http://csrc.nist.gov/groups/STM/cavp/message-authentication.html#testing
  // and https://tools.ietf.org/html/rfc4231.
  static final HmacLegacyPrfTestVector[] HMAC_LEGACY_PRF_TEST_VECTORS = {
      new HmacLegacyPrfTestVector(
          HashType.SHA1,
          "816aa4c3ee066310ac1e6666cf830c375355c3c8ba18cfe1f50a48c988b46272",
          "220248f5e6d7a49335b3f91374f18bb8b0ff5e8b9a5853f3cfb293855d78301d837a0a2eb9e4f056f06c08361"
              + "bd07180ee802651e69726c28910d2baef379606815dcbab01d0dc7acb0ba8e65a2928130da0522f2b2b3d05260"
              + "885cf1c64f14ca3145313c685b0274bf6a1cb38e4f99895c6a8cc72fbe0e52c01766fede78a1a",
          "17cb2e9e98b748b5ae0f7078ea5519e5"),
      new HmacLegacyPrfTestVector(
          HashType.SHA256,
          "6f35628d65813435534b5d67fbdb54cb33403d04e843103e6399f806cb5df95febbdd61236f33245",
          "752cff52e4b90768558e5369e75d97c69643509a5e5904e0a386cbe4d0970ef73f918f675945a9aefe26daea27"
              + "587e8dc909dd56fd0468805f834039b345f855cfe19c44b55af241fff3ffcd8045cd5c288e6c4e284c3720570b"
              + "58e4d47b8feeedc52fd1401f698a209fccfa3b4c0d9a797b046a2759f82a54c41ccd7b5f592b",
          "05d1243e6465ed9620c9aec1c351a186"),
      new HmacLegacyPrfTestVector(
          HashType.SHA384,
          "0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b",
          "4869205468657265",
          "afd03944d84895626b0825f4ab46907f15f9dadbe4101ec682aa034c7cebc59cfaea9ea9076ede7f4af152e8b2fa9cb6"),
      new HmacLegacyPrfTestVector(
          HashType.SHA512,
          "726374c4b8df517510db9159b730f93431e0cd468d4f3821eab0edb93abd0fba46ab4f1ef35d54fec3d85fa89e"
              + "f72ff3d35f22cf5ab69e205c10afcdf4aaf11338dbb12073474fddb556e60b8ee52f91163ba314303ee0c910e6"
              + "4e87fbf302214edbe3f2",
          "ac939659dc5f668c9969c0530422e3417a462c8b665e8db25a883a625f7aa59b89c5ad0ece5712ca17442d1798"
              + "c6dea25d82c5db260cb59c75ae650be56569c1bd2d612cc57e71315917f116bbfa65a0aeb8af7840ee83d3e710"
              + "1c52cf652d2773531b7a6bdd690b846a741816c860819270522a5b0cdfa1d736c501c583d916",
          "bd3d2df6f9d284b421a43e5f9cb94bc4ff88a88243f1f0133bad0fb1791f6569"),
  };

  private LegacyHmacPrfTestUtil() {}
}
