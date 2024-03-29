/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.developer.wise4rmgod.naijaakwa.view;

public interface MainMVP {
    interface view{
        void signinanimation();
        void signupanimation();
        void signin();
        void signup();
    }
    interface presenter {
        void signinbtnanimation();
        void signupbtnanimation();
        void signinbtn();
        void signupbtn();
    }
}
