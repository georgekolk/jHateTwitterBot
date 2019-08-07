package org.metacow.omg;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args)throws Exception {
        ConfigurationBuilder twitterConfigBuilder = new ConfigurationBuilder();
        twitterConfigBuilder.setDebugEnabled(true);
        twitterConfigBuilder.setOAuthConsumerKey("1");
        twitterConfigBuilder.setOAuthConsumerSecret("2");
        twitterConfigBuilder.setOAuthAccessToken("3-3");
        twitterConfigBuilder.setOAuthAccessTokenSecret("4");

        Twitter twitter = new TwitterFactory(twitterConfigBuilder.build()).getInstance();
        String statusMessage = "Watch out this interesting offer I came across today";
        File file = new File("ch.jpg");
        File file2 = new File("kk.jpg");
        File file3 = new File("rr.jpg");
        File file4 = new File("zz.jpg");

        //StatusUpdate status = new StatusUpdate(statusMessage);
        //status.setMedia(file); // set the image to be uploaded here.
        //status.setMedia(file2);
        //twitter.updateStatus(status);


        /*long[] mediaIds = new long[4];

        UploadedMedia media1 = twitter.uploadMedia(file);
        mediaIds[0] = media1.getMediaId();
        UploadedMedia media2 = twitter.uploadMedia(file2);
        mediaIds[1] = media2.getMediaId();

        UploadedMedia media3 = twitter.uploadMedia(file3);
        mediaIds[2] = media3.getMediaId();
        UploadedMedia media4 = twitter.uploadMedia(file4);
        mediaIds[3] = media4.getMediaId();

        StatusUpdate update = new StatusUpdate("multi image upload---");
        update.setMediaIds(mediaIds);

        System.out.println(mediaIds);

        Status stat = twitter.updateStatus(update);
        System.out.println("Successfully updated the status to [" + stat.getText() + "][" + stat.getId() + "].");*/

//eto_prekrasno

        //Twitter unauthenticatedTwitter = new TwitterFactory().getInstance();
//First param of Paging() is the page number, second is the number per page (this is capped around 200 I think.
        Paging paging = new Paging(1, 1000);
        //List<Status> statuses = twitter.getUserTimeline("eto_prekrasno",paging);
List<Status> statuses = twitter.getUserTimeline("kek",paging);

        System.out.println("Showing home timeline.");
        for (Status status : statuses) {

            for (MediaEntity mediaEntity : status.getMediaEntities()) {
                System.out.println(mediaEntity.getType() + ": " + mediaEntity.getMediaURL());


                ExtendedMediaEntity[] extendedMediaEntities = status.getExtendedMediaEntities();
                for (int i = 0; i < extendedMediaEntities.length; i++) {
                    ExtendedMediaEntity extendedMediaEntity = extendedMediaEntities[i];
                    ExtendedMediaEntity.Variant[] variant = extendedMediaEntity.getVideoVariants();
                    if (extendedMediaEntity.getType().equals("video")) {
                        for (int j = 0; j < variant.length; j++) {
                            System.out.println("variant url: " + variant[j].getUrl());
                        }
                        String url = extendedMediaEntity.getURL();
                        System.out.println("extended url: " + extendedMediaEntity.getExpandedURL());
                    }
                }

            }

            System.out.println("fav:" + status.getFavoriteCount() + "rt:" + status.getRetweetCount() + status.getUser().getName() + ":" +
                    status.getText());

        }


    }
}
