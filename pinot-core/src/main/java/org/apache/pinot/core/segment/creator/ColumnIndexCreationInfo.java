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
package org.apache.pinot.core.segment.creator;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.pinot.core.common.Constants;
import org.apache.pinot.core.data.partition.PartitionFunction;


public class ColumnIndexCreationInfo implements Serializable {
  private final boolean createDictionary;
  private final boolean useVarLengthDictionary;
  private final boolean isAutoGenerated;
  private final Object defaultNullValue;
  private final ColumnStatistics columnStatistics;

  public ColumnIndexCreationInfo(ColumnStatistics columnStatistics, boolean createDictionary,
      boolean useVarLengthDictionary, boolean isAutoGenerated, Object defaultNullValue) {
    this.columnStatistics = columnStatistics;
    this.createDictionary = createDictionary;
    this.useVarLengthDictionary = useVarLengthDictionary;
    this.isAutoGenerated = isAutoGenerated;
    this.defaultNullValue = defaultNullValue;
  }

  public boolean isCreateDictionary() {
    return createDictionary;
  }

  public boolean isUseVarLengthDictionary() {
    return useVarLengthDictionary;
  }

  public Object getMin() {
    return columnStatistics.getMinValue();
  }

  public Object getMax() {
    return columnStatistics.getMaxValue();
  }

  public Object getSortedUniqueElementsArray() {
    return columnStatistics.getUniqueValuesSet();
  }

  public int getDistinctValueCount() {
    Object uniqueValArray = columnStatistics.getUniqueValuesSet();
    if (uniqueValArray == null) {
      return Constants.UNKNOWN_CARDINALITY;
    }
    return ArrayUtils.getLength(uniqueValArray);
  }

  public boolean isSorted() {
    return columnStatistics.isSorted();
  }

  public boolean hasNulls() {
    return columnStatistics.hasNull();
  }

  public int getTotalNumberOfEntries() {
    return columnStatistics.getTotalNumberOfEntries();
  }

  public int getMaxNumberOfMultiValueElements() {
    return columnStatistics.getMaxNumberOfMultiValues();
  }

  public boolean isAutoGenerated() {
    return isAutoGenerated;
  }

  public Object getDefaultNullValue() {
    return defaultNullValue;
  }

  public int getLengthOfLongestEntry() {
    return columnStatistics.getLengthOfLargestElement();
  }

  public Set<Integer> getPartitions() {
    return columnStatistics.getPartitions();
  }

  public PartitionFunction getPartitionFunction() {
    return columnStatistics.getPartitionFunction();
  }

  public int getNumPartitions() {
    return columnStatistics.getNumPartitions();
  }
}
