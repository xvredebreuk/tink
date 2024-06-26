// Copyright 2021 Google Inc.
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

package com.google.crypto.tink;

/**
 * Test-only utilities for KmsClients.
 *
 * @deprecated KmsClients is deprecated.
 */
@Deprecated // We do not recommend using this API, but there are no plans to remove it.
public final class KmsClientsTestUtil {

  /** Resets KmsClients, should only be used for testing. */
  @Deprecated // We do not recommend using this API, but there are no plans to remove it.
  public static void reset() {
    KmsClients.reset();
  }

  private KmsClientsTestUtil() {}
}
