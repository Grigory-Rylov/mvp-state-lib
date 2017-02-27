# yet-another-mvp-lib
Yet another MVP library for android

The Library helps forget about android view-life cycle.
Even if process was killed by system, after resore - your view and presenters holds their last state,
without manual storing state to Bundle (I tell you a secret: library do it automatic)

View has single public method - onUpdateState() - it received events from presenter.
Events implements MvpState interface - that is marker interface, inherited from Serializable.
Your State-models must implements MvpState and its fields must implements Serializable.

Add compile "com.github.grigory-rylov:yet-another-mvp-lib:0.1.0" to your build.gradle in app's folder
