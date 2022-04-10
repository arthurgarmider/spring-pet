package org.springframework.samples.petclinic.owner;

import org.springframework.beans.factory.annotation.Autowired;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kinesis.producer.Attempt;
import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import com.amazonaws.services.kinesis.producer.UserRecordFailedException;
import com.amazonaws.services.kinesis.producer.UserRecordResult;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class OwnerManager {

	@Autowired private OwnerRepository ownerRepository;
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	static DynamoDB dynamoDB = new DynamoDB(client);
	public final static String TIMESTAMP_AS_PARTITION_KEY = "TIMESTAMP_AS_PARTITION_KEY"
	public boolean ownerRegistrationManager(Owner owner)
	{
		final SampleProducerConfig config = new SampleProducerConfig();


		final KinesisProducer kinesisProducer = new KinesisProducer(config.transformToKinesisProducerConfiguration());
		ByteBuffer data = ByteBuffer.wrap(owner.toString().getBytes(StandardCharsets.UTF_8));
		String streamName = "privyaStrema";
		ListenableFuture<UserRecordResult> f =
			kinesisProducer.addUserRecord(streamName, TIMESTAMP_AS_PARTITION_KEY, owner);

//		Futures.addCallback(f, myCallback, callbackThreadPool);
	}
}
