package dev.mccue.jdk.httpserver.mustache;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import dev.mccue.jdk.httpserver.Body;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

public final class MustacheBody implements Body {
    private final Template template;
    private final @Nullable Object context;

    private MustacheBody(Template template, @Nullable Object context) {
        this.template = template;
        this.context = context;
    }

    public static MustacheBody of(Template template, @Nullable Object context) {
        return new MustacheBody(template, context);
    }

    public static MustacheBody of(String template, @Nullable Object context) {
        return of(Mustache.compiler().compile(template), context);
    }

    @Override
    public void writeTo(OutputStream outputStream) throws IOException {
        try (var writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            template.execute(context, writer);
        }
    }

    @Override
    public Optional<String> defaultContentType() {
        return Optional.of("text/html; charset=utf-8");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MustacheBody other &&
               Objects.equals(template, other.template) &&
               Objects.equals(context, other.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(template, context);
    }

    @Override
    public String toString() {
        return "MustacheBody[template=" +
               template +
               ", context=" +
               context +
               "]";
    }
}
