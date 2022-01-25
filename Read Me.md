To run the file:
mvn compile exec:java \
-Dexec.mainClass=com.nuneskris.study.gcp.dataflow.template.MyFirstCustom \
-Dexec.args="--runner=DataflowRunner \
--project=java-maven-dataflow \
--stagingLocation=gs://cricket-score-study/staging \
--templateLocation=gs://cricket-score-study/templates/MyFirstWord_metadata
--region=us-west1"

The template file (the _metadata file without the extension) needs be in the location as indicated above.

# IMPORTANANT!!!!! Ensure the POM xml order is correct otherwise there is an issue while runnuing the example.
The below dependency needs to be list first
<dependency>
<groupId>org.apache.beam</groupId>
<artifactId>beam-runners-google-cloud-dataflow-java</artifactId>
<version>2.35.0</version>
<scope>runtime</scope>
</dependency>

This example is based on the wordcount example from the GCP examples.

