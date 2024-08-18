import org.jspecify.annotations.NullMarked;

@NullMarked
module dev.mccue.jdk.httpserver.mustache {
    requires transitive jdk.httpserver;
    requires transitive dev.mccue.jdk.httpserver;
    requires transitive com.samskivert.jmustache;
    requires org.jspecify;

    exports dev.mccue.jdk.httpserver.mustache;
}