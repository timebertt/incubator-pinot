/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pinot.spi.ingestion.segment.uploader;

import java.net.URI;
import org.apache.pinot.spi.annotations.InterfaceStability;
import org.apache.pinot.spi.config.table.TableConfig;


/**
 * Interface for uploading segments to Pinot
 */
@InterfaceStability.Evolving
public interface SegmentUploader {

  /**
   * Initializes the {@link SegmentUploader}
   * @param tableConfig The table config for the segment upload
   */
  void init(TableConfig tableConfig)
      throws Exception;

  /**
   * Uploads the segment tar file to the cluster
   * @param segmentTarFile URI of segment tar file
   */
  void uploadSegment(URI segmentTarFile)
      throws Exception;

  /**
   * Uploads the segments from the segmentDir to the cluster
   * @param segmentDir URI of directory containing segment tar files
   */
  void uploadSegments(URI segmentDir)
      throws Exception;
}
