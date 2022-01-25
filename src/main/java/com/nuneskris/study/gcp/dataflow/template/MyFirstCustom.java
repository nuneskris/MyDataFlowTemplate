package com.nuneskris.study.gcp.dataflow.template;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.SerializableFunction;

public class MyFirstCustom {
    public interface WriteIntsOptions extends PipelineOptions {
        // New runtime parameter, specified by the --fileName
        // option at runtime.
        ValueProvider<String> getFileName();
        void setFileName(ValueProvider<String> value);
    }

    public static void main(String[] args) {
        WriteIntsOptions options =
                PipelineOptionsFactory.fromArgs(args).withValidation()
                        .as(WriteIntsOptions.class);
        Pipeline p = Pipeline.create(options);

        p.apply(Create.of("1", "2", "3"))
                // Write to the computed complete file path.
                .apply("OutputNums", TextIO.write().to(ValueProvider.NestedValueProvider.of(
                        options.getFileName(),
                        new SerializableFunction<String, String>() {
                            @Override
                            public String apply(String file) {
                                return "gs://cricket-score-study/OutputNums.txt";
                            }
                        })));

        p.run();
    }
}
