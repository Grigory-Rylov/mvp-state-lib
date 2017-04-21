# mvp-state-lib
Yet another MVP library for android

The Library helps forget about android view-life cycle.
Even if process was killed by system, after resore - your view and presenters holds their last state,
without manual storing state to Bundle (I tell you a secret: library do it automatic)

View has single public method - onUpdateState() - it received events from presenter.
Events implements MvpState interface - that is marker interface, inherited from Serializable.
Your State-models must implements MvpState and its fields must implements Serializable.

### Download

Grab via Gradle:
```groovy
compile 'com.github.mvplib:mvp-state-lib:0.1.6'
```

License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
