/*
 * Copyright 2010 LinkedIn, Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package azkaban.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import azkaban.app.AzkabanApp;
import azkaban.common.utils.Utils;

/**
 * Web utilities
 * 
 * @author jkreps
 * 
 */
public class WebUtils {

    /**
     * Get the AzkabanApp from the ServletConfig
     * 
     * @param config The ServletConfig
     * @return The app
     */
    public static AzkabanApp getApp(ServletConfig config) {
        AzkabanApp app = (AzkabanApp) config.getServletContext()
                                            .getAttribute(AzkabanServletContextListener.AZKABAN_SERVLET_CONTEXT_KEY);
        if(app == null)
            throw new IllegalStateException("No batch application is defined in the servlet context!");
        else
            return app;
    }

    public static int getInt(HttpServletRequest req, String name, int defaultValue) {
        String value = req.getParameter(name);
        if(value == null)
            return defaultValue;
        else
            return Integer.parseInt(value);
    }

}