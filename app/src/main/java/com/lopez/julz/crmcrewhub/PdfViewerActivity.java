package com.lopez.julz.crmcrewhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.File;

public class PdfViewerActivity extends AppCompatActivity {
    public PDFView pdfViewer;

    public String pdfPath, scId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        pdfPath = getIntent().getExtras().getString("PDF_PATH");
        scId = getIntent().getExtras().getString("SC_ID");

        pdfViewer =  findViewById(R.id.pdfViewer);

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/lineman/" + scId + "/" + pdfPath);
        Log.e("TEST", file.length() == 0 ? "FILE IS BLANK" : file.getAbsoluteFile().canRead() + "");
        pdfViewer.fromFile(file)
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        // Page changed
                    }
                })
                .enableAnnotationRendering(true)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        // PDF document loaded successfully
                    }
                })
                .scrollHandle(new DefaultScrollHandle(PdfViewerActivity.this))
                .spacing(10) // in dp
                .pageFitPolicy(FitPolicy.BOTH)
                .load();;
    }
}