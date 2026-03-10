SENG 272 - Lab 2

ISO 25010 Characteristics
| Characteristic | Code |
|----------------|------|
| Functional Suitability | QC.FS |
| Performance Efficiency | QC.PE |
| Compatibility | QC.CO |
| Usability | QC.US |
| Reliability | QC.RE |
| Security | QC.SE |
| Maintainability | QC.MA |
| Portability | QC.PO |

ISO 25023 Metrics
| Characteristic | Metric | Direction | Unit |
|----------------|--------|-----------|------|
| Functional Suitability | Functional Completeness Ratio | Higher | % |
| Functional Suitability | Functional Correctness Ratio | Higher | % |
| Reliability | Availability Ratio | Higher | % |
| Reliability | Defect Density | Lower | defect/KLOC |
| Performance Efficiency | Response Time | Lower | ms |
| Performance Efficiency | CPU Utilisation Ratio | Lower | % |
| Usability | Task Completion Rate | Higher | % |
| Security | Security Test Coverage | Higher | % |
| Maintainability | Test Coverage Ratio | Higher | % |
| Maintainability | Cyclomatic Complexity | Lower | score |


System Output: 

========================================
SOFTWARE QUALITY EVALUATION REPORT (ISO/IEC 25010)
System: ShopSphere v3.2.1 (Web)
========================================
--- Functional Suitability  [QC.FS] (Weight: 25) ---
Functional Completeness Ratio: 94.0 % -> Score: 4.5 (Higher is better)
Functional Correctness Ratio: 91.0 % -> Score: 4.5 (Higher is better)
>> Dimension Score: 4.5/5 [Excellent Quality]
--- Reliability  [QC.RE] (Weight: 25) ---
Availability Ratio: 99.2 % -> Score: 4.5 (Higher is better)
Defect Density: 2.1 defect/KLOC -> Score: 4.5 (Lower is better)
>> Dimension Score: 4.5/5 [Excellent Quality]
--- Performance Efficiency  [QC.PE] (Weight: 25) ---
Response Time: 220.0 ms -> Score: 4.0 (Lower is better)
CPU Utilisation Ratio: 38.0 % -> Score: 4.0 (Lower is better)
>> Dimension Score: 4.0/5 [Good Quality]
--- Maintainability  [QC.MA] (Weight: 25) ---
Test Coverage Ratio: 72.0 % -> Score: 3.5 (Higher is better)
Cyclomatic Complexity: 8.5 score -> Score: 3.5 (Lower is better)
>> Dimension Score: 3.5/5 [Good Quality]
========================================
OVERALL QUALITY SCORE: 4.1/5 [Good Quality]
========================================
GAP ANALYSIS (ISO/IEC 25010)
========================================
Weakest Characteristic : Maintainability [QC.MA]
Score: 3.5/5 | Gap: 1.5
Level: Good Quality
>> This characteristic requires the most improvement.
========================================