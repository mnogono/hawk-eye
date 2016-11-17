package com.wildcat.ui.dashboard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamVariable;
import com.vaadin.ui.*;


public class SampleDropBox extends DragAndDropWrapper implements
        DropHandler {
    private static final long FILE_SIZE_LIMIT = 2 * 1024 * 1024; // 2MB

    public SampleDropBox(Component root) {
        super(root);
        setDropHandler(this);
        setDragStartMode(DragStartMode.HTML5);
        setHTML5DataFlavor("text/plain",
                "This text was drag'n'dropped from Vaadin.");
        setHTML5DataFlavor("text/html",
                "<h1>HTML Content</h1><p>This text was drag&quot;n&quot;dropped from Vaadin.");

    }

    public void drop(DragAndDropEvent dropEvent) {

        // expecting this to be an html5 drag
        WrapperTransferable tr = (WrapperTransferable) dropEvent
                .getTransferable();
        Html5File[] files = tr.getFiles();
        if (files != null) {
            for (final Html5File html5File : files) {
                final String fileName = html5File.getFileName();

                if (html5File.getFileSize() > FILE_SIZE_LIMIT) {
                    /*
                    getWindow()
                            .showNotification(
                                    "File rejected. Max 2MB files are accepted by Sampler",
                                    Notification.TYPE_WARNING_MESSAGE);
                    */
                    Notification.show("File rejected. Max 2MB files are accepted by Sampler");
                } else {

                    final ByteArrayOutputStream bas = new ByteArrayOutputStream();
                    StreamVariable streamVariable = new StreamVariable() {

                        public OutputStream getOutputStream() {
                            return bas;
                        }

                        public boolean listenProgress() {
                            return false;
                        }

                        public void onProgress(StreamingProgressEvent event) {
                        }

                        public void streamingStarted(
                                StreamingStartEvent event) {
                        }

                        public void streamingFinished(
                                StreamingEndEvent event) {
                            //progress.setVisible(false);
                            showFile(fileName, html5File.getType(), bas);
                        }

                        public void streamingFailed(
                                StreamingErrorEvent event) {
                            //progress.setVisible(false);
                        }

                        public boolean isInterrupted() {
                            return false;
                        }
                    };
                    html5File.setStreamVariable(streamVariable);
                    //progress.setVisible(true);
                }
            }

        } else {
            String text = tr.getText();
            if (text != null) {
                showText(text);
            }
        }
    }

    private void showText(String text) {
        showComponent(new Label(text), "Wrapped text content");
    }

    private void showFile(String name, String type,
                          final ByteArrayOutputStream bas) {
//        // resource for serving the file contents
//        StreamResource.StreamSource streamSource = new StreamResource.StreamSource() {
//            public InputStream getStream() {
//                if (bas != null) {
//                    byte[] byteArray = bas.toByteArray();
//                    return new ByteArrayInputStream(byteArray);
//                }
//                return null;
//            }
//        };
//
//        StreamResource resource = new StreamResource(streamSource, name, getApplication());
//
//        // show the file contents - images only for now
//        Embedded embedded = new Embedded(name, resource);
//        showComponent(embedded, name);
    }

    private void showComponent(Component c, String name) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeUndefined();
        layout.setMargin(true);
        Window w = new Window(name, layout);
        w.setSizeUndefined();
        c.setSizeUndefined();
        //w.addComponent(c);
        w.setContent(c);
        w.center();
        //getWindow().addWindow(w);
        getUI().addWindow(w);

    }

    public AcceptCriterion getAcceptCriterion() {
        return AcceptAll.get();
    }
}
