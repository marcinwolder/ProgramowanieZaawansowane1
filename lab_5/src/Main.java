import MathExpr.Tests;

public class Main {
    public static void main(String[] args) {
        System.out.println("TEST 1:");
        Tests.buildAndPrint();
        System.out.println("\nTEST 2:");
        Tests.buildAndEvaluate();
        System.out.println("\nTEST 3:");
        Tests.defineCircle();
        System.out.println("\nTEST 4:");
        Tests.diffPoly();
        System.out.println("\nTEST 5:");
        Tests.diffCircle();
        System.out.println("\nTEST 6:");
        Tests.newFunctions();
        System.out.println("\nTEST 7:");
        Tests.multinomialTheorem();
        System.out.println("\nTEST 8:");
        Tests.testOptimization();
    }
}

// Wynik metody gradientu (zero po 196 iteracjach)
//[0] goal:607194.566065
//[1] goal:426368.278254
//[2] goal:301649.192223
//[3] goal:215080.629045
//[4] goal:154588.790598
//[5] goal:112021.065151
//[6] goal:81847.318423
//[7] goal:60297.784960
//[8] goal:44789.097649
//[9] goal:33540.924913
//[10] goal:25319.022328
//[11] goal:19262.359908
//[12] goal:14766.337348
//[13] goal:11403.540639
//[14] goal:8869.716797
//[15] goal:6946.754705
//[16] goal:5477.182381
//[17] goal:4346.498270
//[18] goal:3470.857263
//[19] goal:2788.435580
//[20] goal:2253.336772
//[21] goal:1831.262980
//[22] goal:1496.419709
//[23] goal:1229.287866
//[24] goal:1015.009368
//[25] goal:842.209635
//[26] goal:702.133189
//[27] goal:588.005080
//[28] goal:494.556263
//[29] goal:417.668748
//[30] goal:354.108800
//[31] goal:301.325261
//[32] goal:257.296302
//[33] goal:220.412425
//[34] goal:189.386698
//[35] goal:163.185604
//[36] goal:140.975527
//[37] goal:122.081172
//[38] goal:105.953146
//[39] goal:92.142561
//[40] goal:80.281086
//[41] goal:70.065211
//[42] goal:61.243793
//[43] goal:53.608165
//[44] goal:46.984242
//[45] goal:41.226223
//[46] goal:36.211516
//[47] goal:31.836664
//[48] goal:28.014038
//[49] goal:24.669165
//[50] goal:21.738538
//[51] goal:19.167835
//[52] goal:16.910451
//[53] goal:14.926293
//[54] goal:13.180778
//[55] goal:11.644001
//[56] goal:10.290050
//[57] goal:9.096420
//[58] goal:8.043530
//[59] goal:7.114312
//[60] goal:6.293865
//[61] goal:5.569160
//[62] goal:4.928791
//[63] goal:4.362757
//[64] goal:3.862283
//[65] goal:3.419659
//[66] goal:3.028107
//[67] goal:2.681662
//[68] goal:2.375069
//[69] goal:2.103700
//[70] goal:1.863471
//[71] goal:1.650782
//[72] goal:1.462452
//[73] goal:1.295674
//[74] goal:1.147968
//[75] goal:1.017141
//[76] goal:0.901257
//[77] goal:0.798602
//[78] goal:0.707660
//[79] goal:0.627090
//[80] goal:0.555705
//[81] goal:0.492457
//[82] goal:0.436416
//[83] goal:0.386758
//[84] goal:0.342756
//[85] goal:0.303764
//[86] goal:0.269210
//[87] goal:0.238590
//[88] goal:0.211454
//[89] goal:0.187406
//[90] goal:0.166094
//[91] goal:0.147207
//[92] goal:0.130468
//[93] goal:0.115633
//[94] goal:0.102486
//[95] goal:0.090833
//[96] goal:0.080506
//[97] goal:0.071353
//[98] goal:0.063241
//[99] goal:0.056052
//[100] goal:0.049679
//[101] goal:0.044032
//[102] goal:0.039026
//[103] goal:0.034590
//[104] goal:0.030658
//[105] goal:0.027173
//[106] goal:0.024084
//[107] goal:0.021346
//[108] goal:0.018919
//[109] goal:0.016769
//[110] goal:0.014863
//[111] goal:0.013173
//[112] goal:0.011676
//[113] goal:0.010349
//[114] goal:0.009172
//[115] goal:0.008130
//[116] goal:0.007206
//[117] goal:0.006386
//[118] goal:0.005661
//[119] goal:0.005017
//[120] goal:0.004447
//[121] goal:0.003941
//[122] goal:0.003493
//[123] goal:0.003096
//[124] goal:0.002744
//[125] goal:0.002432
//[126] goal:0.002156
//[127] goal:0.001911
//[128] goal:0.001694
//[129] goal:0.001501
//[130] goal:0.001330
//[131] goal:0.001179
//[132] goal:0.001045
//[133] goal:0.000926
//[134] goal:0.000821
//[135] goal:0.000728
//[136] goal:0.000645
//[137] goal:0.000572
//[138] goal:0.000507
//[139] goal:0.000449
//[140] goal:0.000398
//[141] goal:0.000353
//[142] goal:0.000313
//[143] goal:0.000277
//[144] goal:0.000246
//[145] goal:0.000218
//[146] goal:0.000193
//[147] goal:0.000171
//[148] goal:0.000152
//[149] goal:0.000134
//[150] goal:0.000119
//[151] goal:0.000106
//[152] goal:0.000094
//[153] goal:0.000083
//[154] goal:0.000074
//[155] goal:0.000065
//[156] goal:0.000058
//[157] goal:0.000051
//[158] goal:0.000045
//[159] goal:0.000040
//[160] goal:0.000036
//[161] goal:0.000032
//[162] goal:0.000028
//[163] goal:0.000025
//[164] goal:0.000022
//[165] goal:0.000019
//[166] goal:0.000017
//[167] goal:0.000015
//[168] goal:0.000014
//[169] goal:0.000012
//[170] goal:0.000011
//[171] goal:0.000009
//[172] goal:0.000008
//[173] goal:0.000007
//[174] goal:0.000007
//[175] goal:0.000006
//[176] goal:0.000005
//[177] goal:0.000005
//[178] goal:0.000004
//[179] goal:0.000004
//[180] goal:0.000003
//[181] goal:0.000003
//[182] goal:0.000003
//[183] goal:0.000002
//[184] goal:0.000002
//[185] goal:0.000002
//[186] goal:0.000002
//[187] goal:0.000001
//[188] goal:0.000001
//[189] goal:0.000001
//[190] goal:0.000001
//[191] goal:0.000001
//[192] goal:0.000001
//[193] goal:0.000001
//[194] goal:0.000001
//[195] goal:0.000001
//[196] goal:0.000000
//[197] goal:0.000000
//[198] goal:0.000000
//[199] goal:0.000000
//[200] goal:0.000000
//[201] goal:0.000000
//[202] goal:0.000000
//[203] goal:0.000000
//[204] goal:0.000000
//[205] goal:0.000000
//[206] goal:0.000000
//[207] goal:0.000000
//[208] goal:0.000000
//[209] goal:0.000000
//[210] goal:0.000000
//[211] goal:0.000000
//[212] goal:0.000000
//[213] goal:0.000000
//[214] goal:0.000000
//[215] goal:0.000000
//[216] goal:0.000000
//[217] goal:0.000000
//[218] goal:0.000000
//[219] goal:0.000000
//[220] goal:0.000000
//[221] goal:0.000000
//[222] goal:0.000000
//[223] goal:0.000000
//[224] goal:0.000000
//[225] goal:0.000000
//[226] goal:0.000000
//[227] goal:0.000000
//[228] goal:0.000000
//[229] goal:0.000000
//[230] goal:0.000000
//[231] goal:0.000000
//[232] goal:0.000000
//[233] goal:0.000000
//[234] goal:0.000000
//[235] goal:0.000000
//[236] goal:0.000000
//[237] goal:0.000000
//[238] goal:0.000000
//[239] goal:0.000000
//[240] goal:0.000000
//[241] goal:0.000000
//[242] goal:0.000000
//[243] goal:0.000000
//[244] goal:0.000000
//[245] goal:0.000000
//[246] goal:0.000000
//[247] goal:0.000000
//[248] goal:0.000000
//[249] goal:0.000000
//[250] goal:0.000000
//[251] goal:0.000000
//[252] goal:0.000000
//[253] goal:0.000000
//[254] goal:0.000000
//[255] goal:0.000000
//[256] goal:0.000000
//[257] goal:0.000000
//[258] goal:0.000000
//[259] goal:0.000000
//[260] goal:0.000000
//[261] goal:0.000000
//[262] goal:0.000000
//[263] goal:0.000000
//[264] goal:0.000000
//[265] goal:0.000000
//[266] goal:0.000000
//[267] goal:0.000000
//[268] goal:0.000000
//[269] goal:0.000000
//[270] goal:0.000000
//[271] goal:0.000000
//[272] goal:0.000000
//[273] goal:0.000000
//[274] goal:0.000000
//[275] goal:0.000000
//[276] goal:0.000000
//[277] goal:0.000000
//[278] goal:0.000000
//[279] goal:0.000000
//[280] goal:0.000000
//[281] goal:0.000000
//[282] goal:0.000000
//[283] goal:0.000000
//[284] goal:0.000000
//[285] goal:0.000000
//[286] goal:0.000000
//[287] goal:0.000000
//[288] goal:0.000000
//[289] goal:0.000000
//[290] goal:0.000000
//[291] goal:0.000000
//[292] goal:0.000000
//[293] goal:0.000000
//[294] goal:0.000000
//[295] goal:0.000000
//[296] goal:0.000000
//[297] goal:0.000000
//[298] goal:0.000000
//[299] goal:0.000000
//[300] goal:0.000000
//[301] goal:0.000000
//[302] goal:0.000000
//[303] goal:0.000000
//[304] goal:0.000000
//[305] goal:0.000000
//[306] goal:0.000000
//[307] goal:0.000000
//[308] goal:0.000000
//[309] goal:0.000000
//[310] goal:0.000000
//[311] goal:0.000000
//[312] goal:0.000000
//[313] goal:0.000000
//[314] goal:0.000000
//[315] goal:0.000000
//[316] goal:0.000000
//[317] goal:0.000000
//[318] goal:0.000000
//[319] goal:0.000000
//[320] goal:0.000000
//[321] goal:0.000000
//[322] goal:0.000000
//[323] goal:0.000000
//[324] goal:0.000000
//[325] goal:0.000000
//[326] goal:0.000000
//[327] goal:0.000000
//[328] goal:0.000000
//[329] goal:0.000000
//[330] goal:0.000000
//[331] goal:0.000000
//[332] goal:0.000000
//[333] goal:0.000000
//[334] goal:0.000000
//[335] goal:0.000000
//[336] goal:0.000000
//[337] goal:0.000000
//[338] goal:0.000000
//[339] goal:0.000000
//[340] goal:0.000000
//[341] goal:0.000000
//[342] goal:0.000000
//[343] goal:0.000000
//[344] goal:0.000000
//[345] goal:0.000000
//[346] goal:0.000000
//[347] goal:0.000000
//[348] goal:0.000000
//[349] goal:0.000000
//[350] goal:0.000000
//[351] goal:0.000000
//[352] goal:0.000000
//[353] goal:0.000000
//[354] goal:0.000000
//[355] goal:0.000000
//[356] goal:0.000000
//[357] goal:0.000000
//[358] goal:0.000000
//[359] goal:0.000000
//[360] goal:0.000000
//[361] goal:0.000000
//[362] goal:0.000000
//[363] goal:0.000000
//[364] goal:0.000000
//[365] goal:0.000000
//[366] goal:0.000000
//[367] goal:0.000000
//[368] goal:0.000000
//[369] goal:0.000000
//[370] goal:0.000000
//[371] goal:0.000000
//[372] goal:0.000000
//[373] goal:0.000000
//[374] goal:0.000000
//[375] goal:0.000000
//[376] goal:0.000000
//[377] goal:0.000000
//[378] goal:0.000000
//[379] goal:0.000000
//[380] goal:0.000000
//[381] goal:0.000000
//[382] goal:0.000000
//[383] goal:0.000000
//[384] goal:0.000000
//[385] goal:0.000000
//[386] goal:0.000000
//[387] goal:0.000000
//[388] goal:0.000000
//[389] goal:0.000000
//[390] goal:0.000000
//[391] goal:0.000000
//[392] goal:0.000000
//[393] goal:0.000000
//[394] goal:0.000000
//[395] goal:0.000000
//[396] goal:0.000000
//[397] goal:0.000000
//[398] goal:0.000000
//[399] goal:0.000000
//[400] goal:0.000000
//[401] goal:0.000000
//[402] goal:0.000000
//[403] goal:0.000000
//[404] goal:0.000000
//[405] goal:0.000000
//[406] goal:0.000000
//[407] goal:0.000000
//[408] goal:0.000000
//[409] goal:0.000000
//[410] goal:0.000000
//[411] goal:0.000000
//[412] goal:0.000000
//[413] goal:0.000000
//[414] goal:0.000000
//[415] goal:0.000000
//[416] goal:0.000000
//[417] goal:0.000000
//[418] goal:0.000000
//[419] goal:0.000000
//[420] goal:0.000000
//[421] goal:0.000000
//[422] goal:0.000000
//[423] goal:0.000000
//[424] goal:0.000000
//[425] goal:0.000000
//[426] goal:0.000000
//[427] goal:0.000000
//[428] goal:0.000000
//[429] goal:0.000000
//[430] goal:0.000000
//[431] goal:0.000000
//[432] goal:0.000000
//[433] goal:0.000000
//[434] goal:0.000000
//[435] goal:0.000000
//[436] goal:0.000000
//[437] goal:0.000000
//[438] goal:0.000000
//[439] goal:0.000000
//[440] goal:0.000000
//[441] goal:0.000000
//[442] goal:0.000000
//[443] goal:0.000000
//[444] goal:0.000000
//[445] goal:0.000000
//[446] goal:0.000000
//[447] goal:0.000000
//[448] goal:0.000000
//[449] goal:0.000000
//[450] goal:0.000000
//[451] goal:0.000000
//[452] goal:0.000000
//[453] goal:0.000000
//[454] goal:0.000000
//[455] goal:0.000000
//[456] goal:0.000000
//[457] goal:0.000000
//[458] goal:0.000000
//[459] goal:0.000000
//[460] goal:0.000000
//[461] goal:0.000000
//[462] goal:0.000000
//[463] goal:0.000000
//[464] goal:0.000000
//[465] goal:0.000000
//[466] goal:0.000000
//[467] goal:0.000000
//[468] goal:0.000000
//[469] goal:0.000000
//[470] goal:0.000000
//[471] goal:0.000000
//[472] goal:0.000000
//[473] goal:0.000000
//[474] goal:0.000000
//[475] goal:0.000000
//[476] goal:0.000000
//[477] goal:0.000000
//[478] goal:0.000000
//[479] goal:0.000000
//[480] goal:0.000000
//[481] goal:0.000000
//[482] goal:0.000000
//[483] goal:0.000000
//[484] goal:0.000000
//[485] goal:0.000000
//[486] goal:0.000000
//[487] goal:0.000000
//[488] goal:0.000000
//[489] goal:0.000000
//[490] goal:0.000000
//[491] goal:0.000000
//[492] goal:0.000000
//[493] goal:0.000000
//[494] goal:0.000000
//[495] goal:0.000000
//[496] goal:0.000000
//[497] goal:0.000000
//[498] goal:0.000000
//[499] goal:0.000000
//[500] goal:0.000000
//[501] goal:0.000000
//[502] goal:0.000000
//[503] goal:0.000000
//[504] goal:0.000000
//[505] goal:0.000000
//[506] goal:0.000000
//[507] goal:0.000000
//[508] goal:0.000000
//[509] goal:0.000000
//[510] goal:0.000000
//[511] goal:0.000000
//[512] goal:0.000000
//[513] goal:0.000000
//[514] goal:0.000000
//[515] goal:0.000000
//[516] goal:0.000000
//[517] goal:0.000000
//[518] goal:0.000000
//[519] goal:0.000000
//[520] goal:0.000000
//[521] goal:0.000000
//[522] goal:0.000000
//[523] goal:0.000000
//[524] goal:0.000000
//[525] goal:0.000000
//[526] goal:0.000000
//[527] goal:0.000000
//[528] goal:0.000000
//[529] goal:0.000000
//[530] goal:0.000000
//[531] goal:0.000000
//[532] goal:0.000000
//[533] goal:0.000000
//[534] goal:0.000000
//[535] goal:0.000000
//[536] goal:0.000000
//[537] goal:0.000000
//[538] goal:0.000000
//[539] goal:0.000000
//[540] goal:0.000000
//[541] goal:0.000000
//[542] goal:0.000000
//[543] goal:0.000000
//[544] goal:0.000000
//[545] goal:0.000000
//[546] goal:0.000000
//[547] goal:0.000000
//[548] goal:0.000000
//[549] goal:0.000000
//[550] goal:0.000000
//[551] goal:0.000000
//[552] goal:0.000000
//[553] goal:0.000000
//[554] goal:0.000000
//[555] goal:0.000000
//[556] goal:0.000000
//[557] goal:0.000000
//[558] goal:0.000000
//[559] goal:0.000000
//[560] goal:0.000000
//[561] goal:0.000000
//[562] goal:0.000000
//[563] goal:0.000000
//[564] goal:0.000000
//[565] goal:0.000000
//[566] goal:0.000000
//[567] goal:0.000000
//[568] goal:0.000000
//[569] goal:0.000000
//[570] goal:0.000000
//[571] goal:0.000000
//[572] goal:0.000000
//[573] goal:0.000000
//[574] goal:0.000000
//[575] goal:0.000000
//[576] goal:0.000000
//[577] goal:0.000000
//[578] goal:0.000000
//[579] goal:0.000000
//[580] goal:0.000000
//[581] goal:0.000000
//[582] goal:0.000000
//[583] goal:0.000000
//[584] goal:0.000000
//[585] goal:0.000000
//[586] goal:0.000000
//[587] goal:0.000000
//[588] goal:0.000000
//[589] goal:0.000000
//[590] goal:0.000000
//[591] goal:0.000000
//[592] goal:0.000000
//[593] goal:0.000000
//[594] goal:0.000000
//[595] goal:0.000000
//[596] goal:0.000000
//[597] goal:0.000000
//[598] goal:0.000000
//[599] goal:0.000000
//[600] goal:0.000000
//[601] goal:0.000000
//[602] goal:0.000000
//[603] goal:0.000000
//[604] goal:0.000000
//[605] goal:0.000000
//[606] goal:0.000000
//[607] goal:0.000000
//[608] goal:0.000000
//[609] goal:0.000000
//[610] goal:0.000000
//[611] goal:0.000000
//[612] goal:0.000000
//[613] goal:0.000000
//[614] goal:0.000000
//[615] goal:0.000000
//[616] goal:0.000000
//[617] goal:0.000000
//[618] goal:0.000000
//[619] goal:0.000000
//[620] goal:0.000000
//[621] goal:0.000000
//[622] goal:0.000000
//[623] goal:0.000000
//[624] goal:0.000000
//[625] goal:0.000000
//[626] goal:0.000000
//[627] goal:0.000000
//[628] goal:0.000000
//[629] goal:0.000000
//[630] goal:0.000000
//[631] goal:0.000000
//[632] goal:0.000000
//[633] goal:0.000000
//[634] goal:0.000000
//[635] goal:0.000000
//[636] goal:0.000000
//[637] goal:0.000000
//[638] goal:0.000000
//[639] goal:0.000000
//[640] goal:0.000000
//[641] goal:0.000000
//[642] goal:0.000000
//[643] goal:0.000000
//[644] goal:0.000000
//[645] goal:0.000000
//[646] goal:0.000000
//[647] goal:0.000000
//[648] goal:0.000000
//[649] goal:0.000000
//[650] goal:0.000000
//[651] goal:0.000000
//[652] goal:0.000000
//[653] goal:0.000000
//[654] goal:0.000000
//[655] goal:0.000000
//[656] goal:0.000000
//[657] goal:0.000000
//[658] goal:0.000000
//[659] goal:0.000000
//[660] goal:0.000000
//[661] goal:0.000000
//[662] goal:0.000000
//[663] goal:0.000000
//[664] goal:0.000000
//[665] goal:0.000000
//[666] goal:0.000000
//[667] goal:0.000000
//[668] goal:0.000000
//[669] goal:0.000000
//[670] goal:0.000000
//[671] goal:0.000000
//[672] goal:0.000000
//[673] goal:0.000000
//[674] goal:0.000000
//[675] goal:0.000000
//[676] goal:0.000000
//[677] goal:0.000000
//[678] goal:0.000000
//[679] goal:0.000000
//[680] goal:0.000000
//[681] goal:0.000000
//[682] goal:0.000000
//[683] goal:0.000000
//[684] goal:0.000000
//[685] goal:0.000000
//[686] goal:0.000000
//[687] goal:0.000000
//[688] goal:0.000000
//[689] goal:0.000000
//[690] goal:0.000000
//[691] goal:0.000000
//[692] goal:0.000000
//[693] goal:0.000000
//[694] goal:0.000000
//[695] goal:0.000000
//[696] goal:0.000000
//[697] goal:0.000000
//[698] goal:0.000000
//[699] goal:0.000000
//[700] goal:0.000000
//[701] goal:0.000000
//[702] goal:0.000000
//[703] goal:0.000000
//[704] goal:0.000000
//[705] goal:0.000000
//[706] goal:0.000000
//[707] goal:0.000000
//[708] goal:0.000000
//[709] goal:0.000000
//[710] goal:0.000000
//[711] goal:0.000000
//[712] goal:0.000000
//[713] goal:0.000000
//[714] goal:0.000000
//[715] goal:0.000000
//[716] goal:0.000000
//[717] goal:0.000000
//[718] goal:0.000000
//[719] goal:0.000000
//[720] goal:0.000000
//[721] goal:0.000000
//[722] goal:0.000000
//[723] goal:0.000000
//[724] goal:0.000000
//[725] goal:0.000000
//[726] goal:0.000000
//[727] goal:0.000000
//[728] goal:0.000000
//[729] goal:0.000000
//[730] goal:0.000000
//[731] goal:0.000000
//[732] goal:0.000000
//[733] goal:0.000000
//[734] goal:0.000000
//[735] goal:0.000000
//[736] goal:0.000000
//[737] goal:0.000000
//[738] goal:0.000000
//[739] goal:0.000000
//[740] goal:0.000000
//[741] goal:0.000000
//[742] goal:0.000000
//[743] goal:0.000000
//[744] goal:0.000000
//[745] goal:0.000000
//[746] goal:0.000000
//[747] goal:0.000000
//[748] goal:0.000000
//[749] goal:0.000000
//[750] goal:0.000000
//[751] goal:0.000000
//[752] goal:0.000000
//[753] goal:0.000000
//[754] goal:0.000000
//[755] goal:0.000000
//[756] goal:0.000000
//[757] goal:0.000000
//[758] goal:0.000000
//[759] goal:0.000000
//[760] goal:0.000000
//[761] goal:0.000000
//[762] goal:0.000000
//[763] goal:0.000000
//[764] goal:0.000000
//[765] goal:0.000000
//[766] goal:0.000000
//[767] goal:0.000000
//[768] goal:0.000000
//[769] goal:0.000000
//[770] goal:0.000000
//[771] goal:0.000000
//[772] goal:0.000000
//[773] goal:0.000000
//[774] goal:0.000000
//[775] goal:0.000000
//[776] goal:0.000000
//[777] goal:0.000000
//[778] goal:0.000000
//[779] goal:0.000000
//[780] goal:0.000000
//[781] goal:0.000000
//[782] goal:0.000000
//[783] goal:0.000000
//[784] goal:0.000000
//[785] goal:0.000000
//[786] goal:0.000000
//[787] goal:0.000000
//[788] goal:0.000000
//[789] goal:0.000000
//[790] goal:0.000000
//[791] goal:0.000000
//[792] goal:0.000000
//[793] goal:0.000000
//[794] goal:0.000000
//[795] goal:0.000000
//[796] goal:0.000000
//[797] goal:0.000000
//[798] goal:0.000000
//[799] goal:0.000000
//[800] goal:0.000000
//[801] goal:0.000000
//[802] goal:0.000000
//[803] goal:0.000000
//[804] goal:0.000000
//[805] goal:0.000000
//[806] goal:0.000000
//[807] goal:0.000000
//[808] goal:0.000000
//[809] goal:0.000000
//[810] goal:0.000000
//[811] goal:0.000000
//[812] goal:0.000000
//[813] goal:0.000000
//[814] goal:0.000000
//[815] goal:0.000000
//[816] goal:0.000000
//[817] goal:0.000000
//[818] goal:0.000000
//[819] goal:0.000000
//[820] goal:0.000000
//[821] goal:0.000000
//[822] goal:0.000000
//[823] goal:0.000000
//[824] goal:0.000000
//[825] goal:0.000000
//[826] goal:0.000000
//[827] goal:0.000000
//[828] goal:0.000000
//[829] goal:0.000000
//[830] goal:0.000000
//[831] goal:0.000000
//[832] goal:0.000000
//[833] goal:0.000000
//[834] goal:0.000000
//[835] goal:0.000000
//[836] goal:0.000000
//[837] goal:0.000000
//[838] goal:0.000000
//[839] goal:0.000000
//[840] goal:0.000000
//[841] goal:0.000000
//[842] goal:0.000000
//[843] goal:0.000000
//[844] goal:0.000000
//[845] goal:0.000000
//[846] goal:0.000000
//[847] goal:0.000000
//[848] goal:0.000000
//[849] goal:0.000000
//[850] goal:0.000000
//[851] goal:0.000000
//[852] goal:0.000000
//[853] goal:0.000000
//[854] goal:0.000000
//[855] goal:0.000000
//[856] goal:0.000000
//[857] goal:0.000000
//[858] goal:0.000000
//[859] goal:0.000000
//[860] goal:0.000000
//[861] goal:0.000000
//[862] goal:0.000000
//[863] goal:0.000000
//[864] goal:0.000000
//[865] goal:0.000000
//[866] goal:0.000000
//[867] goal:0.000000
//[868] goal:0.000000
//[869] goal:0.000000
//[870] goal:0.000000
//[871] goal:0.000000
//[872] goal:0.000000
//[873] goal:0.000000
//[874] goal:0.000000
//[875] goal:0.000000
//[876] goal:0.000000
//[877] goal:0.000000
//[878] goal:0.000000
//[879] goal:0.000000
//[880] goal:0.000000
//[881] goal:0.000000
//[882] goal:0.000000
//[883] goal:0.000000
//[884] goal:0.000000
//[885] goal:0.000000
//[886] goal:0.000000
//[887] goal:0.000000
//[888] goal:0.000000
//[889] goal:0.000000
//[890] goal:0.000000
//[891] goal:0.000000
//[892] goal:0.000000
//[893] goal:0.000000
//[894] goal:0.000000
//[895] goal:0.000000
//[896] goal:0.000000
//[897] goal:0.000000
//[898] goal:0.000000
//[899] goal:0.000000
//[900] goal:0.000000
//[901] goal:0.000000
//[902] goal:0.000000
//[903] goal:0.000000
//[904] goal:0.000000
//[905] goal:0.000000
//[906] goal:0.000000
//[907] goal:0.000000
//[908] goal:0.000000
//[909] goal:0.000000
//[910] goal:0.000000
//[911] goal:0.000000
//[912] goal:0.000000
//[913] goal:0.000000
//[914] goal:0.000000
//[915] goal:0.000000
//[916] goal:0.000000
//[917] goal:0.000000
//[918] goal:0.000000
//[919] goal:0.000000
//[920] goal:0.000000
//[921] goal:0.000000
//[922] goal:0.000000
//[923] goal:0.000000
//[924] goal:0.000000
//[925] goal:0.000000
//[926] goal:0.000000
//[927] goal:0.000000
//[928] goal:0.000000
//[929] goal:0.000000
//[930] goal:0.000000
//[931] goal:0.000000
//[932] goal:0.000000
//[933] goal:0.000000
//[934] goal:0.000000
//[935] goal:0.000000
//[936] goal:0.000000
//[937] goal:0.000000
//[938] goal:0.000000
//[939] goal:0.000000
//[940] goal:0.000000
//[941] goal:0.000000
//[942] goal:0.000000
//[943] goal:0.000000
//[944] goal:0.000000
//[945] goal:0.000000
//[946] goal:0.000000
//[947] goal:0.000000
//[948] goal:0.000000
//[949] goal:0.000000
//[950] goal:0.000000
//[951] goal:0.000000
//[952] goal:0.000000
//[953] goal:0.000000
//[954] goal:0.000000
//[955] goal:0.000000
//[956] goal:0.000000
//[957] goal:0.000000
//[958] goal:0.000000
//[959] goal:0.000000
//[960] goal:0.000000
//[961] goal:0.000000
//[962] goal:0.000000
//[963] goal:0.000000
//[964] goal:0.000000
//[965] goal:0.000000
//[966] goal:0.000000
//[967] goal:0.000000
//[968] goal:0.000000
//[969] goal:0.000000
//[970] goal:0.000000
//[971] goal:0.000000
//[972] goal:0.000000
//[973] goal:0.000000
//[974] goal:0.000000
//[975] goal:0.000000
//[976] goal:0.000000
//[977] goal:0.000000
//[978] goal:0.000000
//[979] goal:0.000000
//[980] goal:0.000000
//[981] goal:0.000000
//[982] goal:0.000000
//[983] goal:0.000000
//[984] goal:0.000000
//[985] goal:0.000000
//[986] goal:0.000000
//[987] goal:0.000000
//[988] goal:0.000000
//[989] goal:0.000000
//[990] goal:0.000000
//[991] goal:0.000000
//[992] goal:0.000000
//[993] goal:0.000000
//[994] goal:0.000000
//[995] goal:0.000000
//[996] goal:0.000000
//[997] goal:0.000000
//[998] goal:0.000000
//[999] goal:0.000000
//[at end] goal:0.000000
//Process finished with exit code 0