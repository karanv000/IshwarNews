package com.news

import org.junit.Test
import org.junit.Assert.*
import com.google.gson.Gson
import com.news.models.NewsDataModel

class DataValidatorUnitTest {

    companion object {
        const val sampleNewsDataResponse: String = "{\n" +
                "    \"status\": \"OK\",\n" +
                "    \"copyright\": \"Copyright (c) 2021 The New York Times Company.  All Rights Reserved.\",\n" +
                "    \"num_results\": 20,\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/e6559248-0f44-56c2-ae40-e81fecf9cb4a\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/12/us/politics/fda-warning-johnson-johnson-vaccine-nerve-syndrome.html\",\n" +
                "            \"id\": 100000007858362,\n" +
                "            \"asset_id\": 100000007858362,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-12\",\n" +
                "            \"updated\": \"2021-07-13 17:42:54\",\n" +
                "            \"section\": \"U.S.\",\n" +
                "            \"subsection\": \"Politics\",\n" +
                "            \"nytdsection\": \"u.s.\",\n" +
                "            \"adx_keywords\": \"Guillain-Barre Syndrome;Vaccination and Immunization;Drugs (Pharmaceuticals);Coronavirus (2019-nCoV);Disease Rates;Regulation and Deregulation of Industry;United States Politics and Government;your-feed-science;your-feed-healthcare;Johnson & Johnson;Food and Drug Administration;Centers for Disease Control and Prevention\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Sharon LaFraniere and Noah Weiland\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"F.D.A. Attaches Warning of Rare Nerve Syndrome to Johnson & Johnson Vaccine\",\n" +
                "            \"abstract\": \"Federal regulators concluded that the risk of developing the syndrome was low, and that the benefits of the vaccine still strongly outweigh it.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Guillain-Barre Syndrome\",\n" +
                "                \"Vaccination and Immunization\",\n" +
                "                \"Drugs (Pharmaceuticals)\",\n" +
                "                \"Coronavirus (2019-nCoV)\",\n" +
                "                \"Disease Rates\",\n" +
                "                \"Regulation and Deregulation of Industry\",\n" +
                "                \"United States Politics and Government\",\n" +
                "                \"your-feed-science\",\n" +
                "                \"your-feed-healthcare\"\n" +
                "            ],\n" +
                "            \"org_facet\": [\n" +
                "                \"Johnson & Johnson\",\n" +
                "                \"Food and Drug Administration\",\n" +
                "                \"Centers for Disease Control and Prevention\"\n" +
                "            ],\n" +
                "            \"per_facet\": [],\n" +
                "            \"geo_facet\": [],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"Administering the Johnson & Johnson vaccine in Brooklyn in May. About 12.8 million people — or about 8 percent of the fully vaccinated population in the United States — have received the Johnson & Johnson shot.\",\n" +
                "                    \"copyright\": \"James Estrin/The New York Times\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/us/politics/00dc-virus-vaccine/00dc-virus-vaccine-thumbStandard-v3.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/us/politics/00dc-virus-vaccine/00dc-virus-vaccine-mediumThreeByTwo210-v2.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/us/politics/00dc-virus-vaccine/00dc-virus-vaccine-mediumThreeByTwo440-v2.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/df9c5845-0e3d-509a-8aa6-fbeead601aa9\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/14/world/asia/guo-gangtang-china.html\",\n" +
                "            \"id\": 100000007865446,\n" +
                "            \"asset_id\": 100000007865446,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-14\",\n" +
                "            \"updated\": \"2021-07-15 10:11:38\",\n" +
                "            \"section\": \"World\",\n" +
                "            \"subsection\": \"Asia Pacific\",\n" +
                "            \"nytdsection\": \"world\",\n" +
                "            \"adx_keywords\": \"Children and Childhood;Kidnapping and Hostages;Human Trafficking;DNA (Deoxyribonucleic Acid);Movies;Guo Gangtang;China\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Vivian Wang and Joy Dong\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"Parents Who Never Stopped Searching Reunite With Son Abducted 24 Years Ago\",\n" +
                "            \"abstract\": \"Guo Gangtang’s cross-country, decades-long search for his son inspired a movie. Now, there’s an ending fit for Hollywood.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Children and Childhood\",\n" +
                "                \"Kidnapping and Hostages\",\n" +
                "                \"Human Trafficking\",\n" +
                "                \"DNA (Deoxyribonucleic Acid)\",\n" +
                "                \"Movies\"\n" +
                "            ],\n" +
                "            \"org_facet\": [],\n" +
                "            \"per_facet\": [\n" +
                "                \"Guo Gangtang\"\n" +
                "            ],\n" +
                "            \"geo_facet\": [\n" +
                "                \"China\"\n" +
                "            ],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"Guo Gangtang and his wife, Zhang Wenge, were reunited on Sunday with their son who was abducted 24 years ago.\",\n" +
                "                    \"copyright\": \"China Daily, via Reuters\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/08/14/world/14china-reunion-sub/14china-reunion-sub-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/08/14/world/14china-reunion-sub/14china-reunion-sub-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/08/14/world/14china-reunion-sub/14china-reunion-sub-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/d2cec17c-31f4-564f-b904-bd6dc67e6e87\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/12/us/politics/child-tax-credit-payments.html\",\n" +
                "            \"id\": 100000007852184,\n" +
                "            \"asset_id\": 100000007852184,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-12\",\n" +
                "            \"updated\": \"2021-07-13 10:16:14\",\n" +
                "            \"section\": \"U.S.\",\n" +
                "            \"subsection\": \"Politics\",\n" +
                "            \"nytdsection\": \"u.s.\",\n" +
                "            \"adx_keywords\": \"Child Tax Credits and Stipends;United States Politics and Government;Stimulus (Economic);American Rescue Plan (2021);Welfare (US);Children and Childhood;Poverty;Biden, Joseph R Jr;United States\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Jason DeParle\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"Monthly Payments to Families With Children to Begin\",\n" +
                "            \"abstract\": \"The Biden administration will send up to \$300 per child a month to most American families thanks to a temporary increase in the child tax credit that advocates hope to extend.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Child Tax Credits and Stipends\",\n" +
                "                \"United States Politics and Government\",\n" +
                "                \"Stimulus (Economic)\",\n" +
                "                \"American Rescue Plan (2021)\",\n" +
                "                \"Welfare (US)\",\n" +
                "                \"Children and Childhood\",\n" +
                "                \"Poverty\"\n" +
                "            ],\n" +
                "            \"org_facet\": [],\n" +
                "            \"per_facet\": [\n" +
                "                \"Biden, Joseph R Jr\"\n" +
                "            ],\n" +
                "            \"geo_facet\": [\n" +
                "                \"United States\"\n" +
                "            ],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"Students at a summer program in New York this month. Nearly nine in 10 children will qualify for the new monthly payments under an expanded version of the child tax credit.\",\n" +
                "                    \"copyright\": \"Jose A. Alvarado Jr. for The New York Times\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/10/us/politics/10dc-childtax1-sub/10dc-childtax1-sub-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/10/us/politics/10dc-childtax1-sub/merlin_190446834_14743110-1b6d-4c88-8238-4ef148d43bc7-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/10/us/politics/10dc-childtax1-sub/merlin_190446834_14743110-1b6d-4c88-8238-4ef148d43bc7-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/420eab5b-7717-5c13-aceb-6ea7a847f693\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/09/us/abortion-law-regulations-texas.html\",\n" +
                "            \"id\": 100000007830938,\n" +
                "            \"asset_id\": 100000007830938,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-09\",\n" +
                "            \"updated\": \"2021-07-14 20:41:25\",\n" +
                "            \"section\": \"U.S.\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"u.s.\",\n" +
                "            \"adx_keywords\": \"Abortion;Law and Legislation;State Legislatures;Planned Parenthood Federation of America;Texas;Lubbock (Tex)\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Sabrina Tavernise\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"Citizens, Not the State, Will Enforce New Abortion Law in Texas\",\n" +
                "            \"abstract\": \"The measure bans abortions after about six weeks of pregnancy. And it effectively deputizes ordinary citizens to sue people involved in the process.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Abortion\",\n" +
                "                \"Law and Legislation\",\n" +
                "                \"State Legislatures\"\n" +
                "            ],\n" +
                "            \"org_facet\": [\n" +
                "                \"Planned Parenthood Federation of America\"\n" +
                "            ],\n" +
                "            \"per_facet\": [],\n" +
                "            \"geo_facet\": [\n" +
                "                \"Texas\",\n" +
                "                \"Lubbock (Tex)\"\n" +
                "            ],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"Anti-abortion protesters stand near the gate of the Texas State Capitol after Gov. Greg Abbott signed a bill outlawing abortions after a fetal heartbeat is detected in May in Austin, Texas.\",\n" +
                "                    \"copyright\": \"Sergio Flores/Getty Images\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/06/30/us/30abortion-1/30abortion-1-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/06/30/us/30abortion-1/30abortion-1-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/06/30/us/30abortion-1/30abortion-1-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/0ad2a673-3197-56a7-a0b5-6886f6d0669f\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/08/business/mark-zuckerberg-sheryl-sandberg-facebook.html\",\n" +
                "            \"id\": 100000007845861,\n" +
                "            \"asset_id\": 100000007845861,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-08\",\n" +
                "            \"updated\": \"2021-07-09 22:31:26\",\n" +
                "            \"section\": \"Business\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"business\",\n" +
                "            \"adx_keywords\": \"Social Media;Storming of the US Capitol (Jan, 2021);Computers and the Internet;Rumors and Misinformation;Content Type: Personal Profile;United States Politics and Government;Freedom of Speech and Expression;Right-Wing Extremism and Alt-Right;Sandberg, Sheryl K;Zuckerberg, Mark E;Trump, Donald J;Pelosi, Nancy;Facebook Inc\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Sheera Frenkel and Cecilia Kang\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"Mark Zuckerberg and Sheryl Sandberg’s Partnership Did Not Survive Trump\",\n" +
                "            \"abstract\": \"The company they built is wildly successful. But her Washington wisdom didn’t hold up, and neither did their close working relationship.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Social Media\",\n" +
                "                \"Storming of the US Capitol (Jan, 2021)\",\n" +
                "                \"Computers and the Internet\",\n" +
                "                \"Rumors and Misinformation\",\n" +
                "                \"Content Type: Personal Profile\",\n" +
                "                \"United States Politics and Government\",\n" +
                "                \"Freedom of Speech and Expression\",\n" +
                "                \"Right-Wing Extremism and Alt-Right\"\n" +
                "            ],\n" +
                "            \"org_facet\": [\n" +
                "                \"Facebook Inc\"\n" +
                "            ],\n" +
                "            \"per_facet\": [\n" +
                "                \"Sandberg, Sheryl K\",\n" +
                "                \"Zuckerberg, Mark E\",\n" +
                "                \"Trump, Donald J\",\n" +
                "                \"Pelosi, Nancy\"\n" +
                "            ],\n" +
                "            \"geo_facet\": [],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"\",\n" +
                "                    \"copyright\": \"Taylor Callery\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/08/business/08facebook-promo-hp/08facebook-promo-hp-thumbStandard-v2.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/08/business/08facebook-promo-hp/08facebook-promo-hp-mediumThreeByTwo210-v2.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/08/business/08facebook-promo-hp/08facebook-promo-hp-mediumThreeByTwo440-v2.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/0eb05048-fb4d-53e7-9bee-151e6d3a1807\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/09/business/student-loans-wiped-out.html\",\n" +
                "            \"id\": 100000007858397,\n" +
                "            \"asset_id\": 100000007858397,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-09\",\n" +
                "            \"updated\": \"2021-07-09 22:57:36\",\n" +
                "            \"section\": \"Business\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"business\",\n" +
                "            \"adx_keywords\": \"Student Loans;Education (K-12);For-Profit Schools;Cardona, Miguel A (1975- );Education Department (US)\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Tara Siegel Bernard\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"The Education Dept. will wipe out \$55 million in student loans for borrowers at 3 institutions.\",\n" +
                "            \"abstract\": \"The department approved 1,800 claims from students who attended Westwood College, Marinello Schools of Beauty and the Court Reporting Institute.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Student Loans\",\n" +
                "                \"Education (K-12)\",\n" +
                "                \"For-Profit Schools\"\n" +
                "            ],\n" +
                "            \"org_facet\": [\n" +
                "                \"Education Department (US)\"\n" +
                "            ],\n" +
                "            \"per_facet\": [\n" +
                "                \"Cardona, Miguel A (1975- )\"\n" +
                "            ],\n" +
                "            \"geo_facet\": [],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"\",\n" +
                "                    \"copyright\": \"Ting Shen for The New York Times\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/business/09economy-briefing-studentloans02/09economy-briefing-studentloans02-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/business/09economy-briefing-studentloans02/merlin_168744000_56fba365-4c68-4e7a-9173-e786f502394e-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/business/09economy-briefing-studentloans02/merlin_168744000_56fba365-4c68-4e7a-9173-e786f502394e-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/c8847900-bf45-5645-96ba-3a420e204504\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/14/us/apt-cape-cod-restaurant-workers-covid.html\",\n" +
                "            \"id\": 100000007864258,\n" +
                "            \"asset_id\": 100000007864258,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-14\",\n" +
                "            \"updated\": \"2021-07-15 10:40:06\",\n" +
                "            \"section\": \"U.S.\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"u.s.\",\n" +
                "            \"adx_keywords\": \"Restaurants;Waiters and Waitresses;Shortages;Workplace Hazards and Violations;Quarantine (Life and Culture);Coronavirus Reopenings;Apt Cape Cod (Brewster, Mass, Restaurant);Brewster (Mass);Cape Cod (Mass)\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Neil Vigdor\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"Restaurant Shuts Down for a ‘Day of Kindness’ After Customers Make Its Staff Cry\",\n" +
                "            \"abstract\": \"The owners of Apt Cape Cod, a farm-to-table restaurant in Brewster, Mass., drew a line in the sand against customers’ rude behavior since being allowed to fully reopen.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Restaurants\",\n" +
                "                \"Waiters and Waitresses\",\n" +
                "                \"Shortages\",\n" +
                "                \"Workplace Hazards and Violations\",\n" +
                "                \"Quarantine (Life and Culture)\",\n" +
                "                \"Coronavirus Reopenings\"\n" +
                "            ],\n" +
                "            \"org_facet\": [\n" +
                "                \"Apt Cape Cod (Brewster, Mass, Restaurant)\"\n" +
                "            ],\n" +
                "            \"per_facet\": [],\n" +
                "            \"geo_facet\": [\n" +
                "                \"Brewster (Mass)\",\n" +
                "                \"Cape Cod (Mass)\"\n" +
                "            ],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"Brandi Felt Castellano, left, and Regina Felt Castellano are the owners of Apt Cape Cod.\",\n" +
                "                    \"copyright\": \"via Brandi Felt Castellano\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/14/nyregion/14xp-restauran/14xp-restauran-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/14/nyregion/14xp-restauran/14xp-restauran-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/14/nyregion/14xp-restauran/14xp-restauran-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/f28490dc-9b11-57f2-904a-7ce45d1f44c7\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/14/travel/passport-renewal-time-delay-delivery-wait.html\",\n" +
                "            \"id\": 100000007859084,\n" +
                "            \"asset_id\": 100000007859084,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-14\",\n" +
                "            \"updated\": \"2021-07-15 10:27:03\",\n" +
                "            \"section\": \"Travel\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"travel\",\n" +
                "            \"adx_keywords\": \"Passports;Travel and Vacations;Coronavirus (2019-nCoV);State Department\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Debra Kamin\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"Need to Renew Your Passport? Good Luck.\",\n" +
                "            \"abstract\": \"An appointment to renew or obtain a passport is an elusive proposition these days, as those who let their documents lapse during the pandemic are finding out.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Passports\",\n" +
                "                \"Travel and Vacations\",\n" +
                "                \"Coronavirus (2019-nCoV)\"\n" +
                "            ],\n" +
                "            \"org_facet\": [\n" +
                "                \"State Department\"\n" +
                "            ],\n" +
                "            \"per_facet\": [],\n" +
                "            \"geo_facet\": [],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"\",\n" +
                "                    \"copyright\": \"Miguel Porlan\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/14/travel/14PASSPORTRENEWALS/14PASSPORTRENEWALS-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/14/travel/14PASSPORTRENEWALS/14PASSPORTRENEWALS-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/14/travel/14PASSPORTRENEWALS/14PASSPORTRENEWALS-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/fd5db9a7-5528-592c-b3bf-5dc1e7a1674e\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/09/us/zaila-avant-garde-spelling-bee-winner.html\",\n" +
                "            \"id\": 100000007857939,\n" +
                "            \"asset_id\": 100000007857939,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-09\",\n" +
                "            \"updated\": \"2021-07-11 14:27:39\",\n" +
                "            \"section\": \"U.S.\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"u.s.\",\n" +
                "            \"adx_keywords\": \"Spelling;Scripps National Spelling Bee;Black People;Avant-garde, Zaila;Harvey (La)\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Maria Cramer and Alan Yuhas\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"Zaila Avant-garde Makes Spelling History, and Other Moments From the Bee\",\n" +
                "            \"abstract\": \"Zaila, a 14-year-old from Harvey, La., won on the word “Murraya.” She became the first Black American to win the Scripps National Spelling Bee in almost 100 years of contests.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Spelling\",\n" +
                "                \"Scripps National Spelling Bee\",\n" +
                "                \"Black People\"\n" +
                "            ],\n" +
                "            \"org_facet\": [],\n" +
                "            \"per_facet\": [\n" +
                "                \"Avant-garde, Zaila\"\n" +
                "            ],\n" +
                "            \"geo_facet\": [\n" +
                "                \"Harvey (La)\"\n" +
                "            ],\n" +
                "            \"media\": [],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/a46ffd8e-8af0-5b99-a615-a48ab80b4581\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/09/nyregion/rent-landlord-eviction-moratorium.html\",\n" +
                "            \"id\": 100000007806867,\n" +
                "            \"asset_id\": 100000007806867,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-09\",\n" +
                "            \"updated\": \"2021-07-10 12:51:37\",\n" +
                "            \"section\": \"New York\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"new york\",\n" +
                "            \"adx_keywords\": \"Landlords;Real Estate and Housing (Residential);Renting and Leasing (Real Estate);Evictions;Suits and Litigation (Civil);Ozone Park (Queens, NY)\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Matthew Haag\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"A Landlord Says Her Tenants Are Terrorizing Her. She Can’t Evict Them.\",\n" +
                "            \"abstract\": \"A landlord in Queens says her tenants curse and spit at her and owe more than \$23,000 in rent. But an eviction moratorium keeps them from being kicked out.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Landlords\",\n" +
                "                \"Real Estate and Housing (Residential)\",\n" +
                "                \"Renting and Leasing (Real Estate)\",\n" +
                "                \"Evictions\",\n" +
                "                \"Suits and Litigation (Civil)\"\n" +
                "            ],\n" +
                "            \"org_facet\": [],\n" +
                "            \"per_facet\": [],\n" +
                "            \"geo_facet\": [\n" +
                "                \"Ozone Park (Queens, NY)\"\n" +
                "            ],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"Vanie Mangal in the home her mother owns in South Ozone Park, Queens. She said the tenants downstairs have not paid rent for over a year and have been abusive toward her.\",\n" +
                "                    \"copyright\": \"Jose A. Alvarado Jr. for The New York Times\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/06/24/nyregion/00nyreopen-landlord-1/00nyreopen-landlord-1-thumbStandard-v2.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/06/24/nyregion/00nyreopen-landlord-1/00nyreopen-landlord-1-mediumThreeByTwo210-v2.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/06/24/nyregion/00nyreopen-landlord-1/00nyreopen-landlord-1-mediumThreeByTwo440-v2.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/c124d28b-8d50-537b-8d0d-5fd9ffb525ba\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/09/opinion/religious-right-america.html\",\n" +
                "            \"id\": 100000007857168,\n" +
                "            \"asset_id\": 100000007857168,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-09\",\n" +
                "            \"updated\": \"2021-07-10 13:48:28\",\n" +
                "            \"section\": \"Opinion\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"opinion\",\n" +
                "            \"adx_keywords\": \"Evangelical Movement;Christians and Christianity;Whites;Conservatism (US Politics);QAnon;Right-Wing Extremism and Alt-Right;Fringe Groups and Movements;Jones, Robert P;Southern Baptist Convention;United States\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Michelle Goldberg\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"The Christian Right Is in Decline, and It’s Taking America With It\",\n" +
                "            \"abstract\": \"White evangelicals can't tolerate becoming just another subculture.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Evangelical Movement\",\n" +
                "                \"Christians and Christianity\",\n" +
                "                \"Whites\",\n" +
                "                \"Conservatism (US Politics)\",\n" +
                "                \"QAnon\",\n" +
                "                \"Right-Wing Extremism and Alt-Right\",\n" +
                "                \"Fringe Groups and Movements\"\n" +
                "            ],\n" +
                "            \"org_facet\": [\n" +
                "                \"Southern Baptist Convention\"\n" +
                "            ],\n" +
                "            \"per_facet\": [\n" +
                "                \"Jones, Robert P\"\n" +
                "            ],\n" +
                "            \"geo_facet\": [\n" +
                "                \"United States\"\n" +
                "            ],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"\",\n" +
                "                    \"copyright\": \"Mark Peterson/Redux\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/opinion/09goldberg-hand-horizon/09goldberg-hand-horizon-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/opinion/09goldberg-hand-horizon/09goldberg-hand-horizon-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/opinion/09goldberg-hand-horizon/09goldberg-hand-horizon-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/196dd9cd-ac28-5d22-bde7-5d58f30c580d\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/09/health/cdc-schools-reopening-guidelines.html\",\n" +
                "            \"id\": 100000007858506,\n" +
                "            \"asset_id\": 100000007858506,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-09\",\n" +
                "            \"updated\": \"2021-07-12 17:25:16\",\n" +
                "            \"section\": \"Health\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"health\",\n" +
                "            \"adx_keywords\": \"Coronavirus Reopenings;Education (K-12);Disease Rates;Coronavirus (2019-nCoV);Pediatric Inflammatory Multisystem Syndrome (PIMS);Children and Childhood;Quarantines;Vaccination and Immunization;Parenting;Centers for Disease Control and Prevention;United States\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Sheryl Gay Stolberg, Emily Anthes, Sarah Mervosh and Kate Taylor\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"The C.D.C. Issues New School Guidance, With Emphasis on Full Reopening\",\n" +
                "            \"abstract\": \"The guidance acknowledges that many students have suffered from months of virtual learning.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Coronavirus Reopenings\",\n" +
                "                \"Education (K-12)\",\n" +
                "                \"Disease Rates\",\n" +
                "                \"Coronavirus (2019-nCoV)\",\n" +
                "                \"Pediatric Inflammatory Multisystem Syndrome (PIMS)\",\n" +
                "                \"Children and Childhood\",\n" +
                "                \"Quarantines\",\n" +
                "                \"Vaccination and Immunization\",\n" +
                "                \"Parenting\"\n" +
                "            ],\n" +
                "            \"org_facet\": [\n" +
                "                \"Centers for Disease Control and Prevention\"\n" +
                "            ],\n" +
                "            \"per_facet\": [],\n" +
                "            \"geo_facet\": [\n" +
                "                \"United States\"\n" +
                "            ],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"A classroom in Raleigh, N.C., last fall. The C.D.C. said physical distance and mask-wearing remained important, but schools should open even if they could not fully comply with the recommendations.\",\n" +
                "                    \"copyright\": \"Juli Leonard/The News & Observer, via Associated Press\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/science/09DC-VIRUS-SCHOOLS1/09DC-VIRUS-SCHOOLS1-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/science/09DC-VIRUS-SCHOOLS1/09DC-VIRUS-SCHOOLS1-mediumThreeByTwo210-v2.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/09/science/09DC-VIRUS-SCHOOLS1/09DC-VIRUS-SCHOOLS1-mediumThreeByTwo440-v2.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/5895ae7b-687a-520c-9d85-da625b585416\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/08/movies/legally-blonde-oral-history.html\",\n" +
                "            \"id\": 100000007839096,\n" +
                "            \"asset_id\": 100000007839096,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-08\",\n" +
                "            \"updated\": \"2021-07-09 12:26:12\",\n" +
                "            \"section\": \"Movies\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"movies\",\n" +
                "            \"adx_keywords\": \"Movies;Women and Girls;Witherspoon, Reese;Basil, Toni;Coolidge, Jennifer;Ubach, Alanna;Larter, Ali;Smith, Kirsten;McCullah, Karen\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Ilana Kaplan\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"‘Legally Blonde’ Oral History: From Raunchy Script to Feminist Classic\",\n" +
                "            \"abstract\": \"Along the way, adult zingers were edited out, Jennifer Coolidge struggled with the “bend and snap” and the ending was changed at least three times.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Movies\",\n" +
                "                \"Women and Girls\"\n" +
                "            ],\n" +
                "            \"org_facet\": [],\n" +
                "            \"per_facet\": [\n" +
                "                \"Witherspoon, Reese\",\n" +
                "                \"Basil, Toni\",\n" +
                "                \"Coolidge, Jennifer\",\n" +
                "                \"Ubach, Alanna\",\n" +
                "                \"Larter, Ali\",\n" +
                "                \"Smith, Kirsten\",\n" +
                "                \"McCullah, Karen\"\n" +
                "            ],\n" +
                "            \"geo_facet\": [],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"\",\n" +
                "                    \"copyright\": \"Karen McCullah\",\n" +
                "                    \"approved_for_syndication\": 0,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/11/nyregion/11legally-blonde-promo-2/merlin_190195509_2266d0c6-18cf-46bd-9f42-300d01630ff6-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/11/nyregion/11legally-blonde-promo-2/merlin_190195509_2266d0c6-18cf-46bd-9f42-300d01630ff6-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/11/nyregion/11legally-blonde-promo-2/merlin_190195509_2266d0c6-18cf-46bd-9f42-300d01630ff6-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/9cc28af1-82e4-58bf-8509-d64af22dea92\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/08/health/delta-variant-covid-vaccine-immunity.html\",\n" +
                "            \"id\": 100000007855360,\n" +
                "            \"asset_id\": 100000007855360,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-08\",\n" +
                "            \"updated\": \"2021-07-14 09:09:36\",\n" +
                "            \"section\": \"Health\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"health\",\n" +
                "            \"adx_keywords\": \"Disease Rates;Coronavirus (2019-nCoV);Immune System;Vaccination and Immunization;Antibodies\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Apoorva Mandavilli\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"How does the Delta variant dodge the immune system? Scientists find clues.\",\n" +
                "            \"abstract\": \"One dose of vaccine is not enough to protect against the Delta and Beta variants, new research suggests, and even people who have had Covid-19 should be immunized.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Disease Rates\",\n" +
                "                \"Coronavirus (2019-nCoV)\",\n" +
                "                \"Immune System\",\n" +
                "                \"Vaccination and Immunization\",\n" +
                "                \"Antibodies\"\n" +
                "            ],\n" +
                "            \"org_facet\": [],\n" +
                "            \"per_facet\": [],\n" +
                "            \"geo_facet\": [],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"A member of the U.S. military and an employee of the New Jersey Institute of Technology prepared doses of the Pfizer-BioNTech vaccine at an immunization site in Newark last month.\",\n" +
                "                    \"copyright\": \"Bryan Anselm for The New York Times\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/08/us/08virus-briefing-delta/08virus-briefing-delta-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/08/us/08virus-briefing-delta/08virus-briefing-delta-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/08/us/08virus-briefing-delta/08virus-briefing-delta-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/8d67105a-c741-57ee-bb17-b8944ad23e38\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/11/world/americas/cuba-crisis-protests.html\",\n" +
                "            \"id\": 100000007861076,\n" +
                "            \"asset_id\": 100000007861076,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-11\",\n" +
                "            \"updated\": \"2021-07-12 20:00:50\",\n" +
                "            \"section\": \"World\",\n" +
                "            \"subsection\": \"Americas\",\n" +
                "            \"nytdsection\": \"world\",\n" +
                "            \"adx_keywords\": \"Demonstrations, Protests and Riots;Economic Conditions and Trends;Coronavirus (2019-nCoV);Shortages;Food Insecurity;Politics and Government;Diaz-Canel Bermudez, Miguel;Cuba\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Frances Robles\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"Cubans Denounce ‘Misery’ in Biggest Protests in Decades\",\n" +
                "            \"abstract\": \"The rallies, widely viewed as astonishing for a country that limits dissent, were set off by economic crises worsened by the pandemic.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Demonstrations, Protests and Riots\",\n" +
                "                \"Economic Conditions and Trends\",\n" +
                "                \"Coronavirus (2019-nCoV)\",\n" +
                "                \"Shortages\",\n" +
                "                \"Food Insecurity\",\n" +
                "                \"Politics and Government\"\n" +
                "            ],\n" +
                "            \"org_facet\": [],\n" +
                "            \"per_facet\": [\n" +
                "                \"Diaz-Canel Bermudez, Miguel\"\n" +
                "            ],\n" +
                "            \"geo_facet\": [\n" +
                "                \"Cuba\"\n" +
                "            ],\n" +
                "            \"media\": [],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/43a0a95d-de8d-50fd-8f4a-7827277c60e8\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/13/well/mind/sleep-insomnia-tips.html\",\n" +
                "            \"id\": 100000007858738,\n" +
                "            \"asset_id\": 100000007858738,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-13\",\n" +
                "            \"updated\": \"2021-07-14 09:03:01\",\n" +
                "            \"section\": \"Well\",\n" +
                "            \"subsection\": \"Mind\",\n" +
                "            \"nytdsection\": \"well\",\n" +
                "            \"adx_keywords\": \"Content Type: Service;Sleep;Meditation;Anxiety and Stress;Insomnia;Mobile Applications;Melatonin (Hormone)\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Anahad O’Connor\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"I’m Often Wide Awake at 3 A.M. How Do I Get Back to Sleep?\",\n" +
                "            \"abstract\": \"Sleep experts offer advice on sleeping soundly through the night.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Content Type: Service\",\n" +
                "                \"Sleep\",\n" +
                "                \"Meditation\",\n" +
                "                \"Anxiety and Stress\",\n" +
                "                \"Insomnia\",\n" +
                "                \"Mobile Applications\",\n" +
                "                \"Melatonin (Hormone)\"\n" +
                "            ],\n" +
                "            \"org_facet\": [],\n" +
                "            \"per_facet\": [],\n" +
                "            \"geo_facet\": [],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"\",\n" +
                "                    \"copyright\": \"Getty Images\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/13/well/13askwell-sleep1/13askwell-sleep1-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/13/well/13askwell-sleep1/13askwell-sleep1-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/13/well/13askwell-sleep1/13askwell-sleep1-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/b7b7bd06-2447-5f1f-86f7-ce132ad008b4\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/07/style/sex-educator-methods-defense.html\",\n" +
                "            \"id\": 100000007836850,\n" +
                "            \"asset_id\": 100000007836850,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-07\",\n" +
                "            \"updated\": \"2021-07-08 13:43:50\",\n" +
                "            \"section\": \"Style\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"style\",\n" +
                "            \"adx_keywords\": \"Education (K-12);Private and Sectarian Schools;Sex Education;Content Type: Personal Profile;Fonte, Justine Ang;Dalton School;Manhattan (NYC)\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Valeriya Safronova\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"A Private-School Sex Educator Defends Her Methods\",\n" +
                "            \"abstract\": \"After nine years at Dalton, why was Justine Ang Fonte suddenly being pilloried by parents?\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Education (K-12)\",\n" +
                "                \"Private and Sectarian Schools\",\n" +
                "                \"Sex Education\",\n" +
                "                \"Content Type: Personal Profile\"\n" +
                "            ],\n" +
                "            \"org_facet\": [\n" +
                "                \"Dalton School\"\n" +
                "            ],\n" +
                "            \"per_facet\": [\n" +
                "                \"Fonte, Justine Ang\"\n" +
                "            ],\n" +
                "            \"geo_facet\": [\n" +
                "                \"Manhattan (NYC)\"\n" +
                "            ],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"Justine Ang Fonte, a sex-positive educator, recently became the subject of angry reports after parents at two elite New York City schools complained about her curriculum.\",\n" +
                "                    \"copyright\": \"Josefina Santos for The New York Times\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/08/fashion/01sexeducator-1-color/01sexeducator-1-color-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/08/fashion/01sexeducator-1-color/01sexeducator-1-color-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/08/fashion/01sexeducator-1-color/01sexeducator-1-color-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://interactive/f801a85a-70df-547e-9133-822e315c0148\",\n" +
                "            \"url\": \"https://www.nytimes.com/interactive/2018/08/30/climate/how-much-hotter-is-your-hometown.html\",\n" +
                "            \"id\": 100000006079302,\n" +
                "            \"asset_id\": 100000006079302,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2018-08-30\",\n" +
                "            \"updated\": \"2021-07-15 11:26:05\",\n" +
                "            \"section\": \"Climate\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"climate\",\n" +
                "            \"adx_keywords\": \"\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Nadja Popovich, Blacki Migliozzi, Rumsey Taylor, Josh Williams and Derek Watkins\",\n" +
                "            \"type\": \"Interactive\",\n" +
                "            \"title\": \"How Much Hotter Is Your Hometown Than When You Were Born?\",\n" +
                "            \"abstract\": \"See how days at or above 90 degrees Fahrenheit have changed in your lifetime and how much hotter it could get.\",\n" +
                "            \"des_facet\": [],\n" +
                "            \"org_facet\": [],\n" +
                "            \"per_facet\": [],\n" +
                "            \"geo_facet\": [],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"\",\n" +
                "                    \"caption\": \"\",\n" +
                "                    \"copyright\": \"\",\n" +
                "                    \"approved_for_syndication\": 0,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2018/08/30/us/how-much-hotter-is-your-hometown-promo-1535677591454/how-much-hotter-is-your-hometown-promo-1535677591454-thumbStandard-v2.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2018/08/30/us/how-much-hotter-is-your-hometown-promo-1535677591454/how-much-hotter-is-your-hometown-promo-1535677591454-mediumThreeByTwo210-v2.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2018/08/30/us/how-much-hotter-is-your-hometown-promo-1535677591454/how-much-hotter-is-your-hometown-promo-1535677591454-mediumThreeByTwo440-v2.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/2d625644-4e21-5454-9997-8f5f812f3e3b\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/07/world/africa/jacob-zuma-arrested-south-africa.html\",\n" +
                "            \"id\": 100000007853446,\n" +
                "            \"asset_id\": 100000007853446,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-07\",\n" +
                "            \"updated\": \"2021-07-12 15:16:13\",\n" +
                "            \"section\": \"World\",\n" +
                "            \"subsection\": \"Africa\",\n" +
                "            \"nytdsection\": \"world\",\n" +
                "            \"adx_keywords\": \"Corruption (Institutional);Politics and Government;Apartheid (Policy);Demonstrations, Protests and Riots;Zuma, Jacob G;African National Congress;South Africa\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By John Eligon\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"Jacob Zuma, Former South African President, Is Arrested\",\n" +
                "            \"abstract\": \"The Constitutional Court had found him guilty of contempt for failing to appear before a commission investigating corruption accusations that tainted a president once best known for fighting apartheid.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"Corruption (Institutional)\",\n" +
                "                \"Politics and Government\",\n" +
                "                \"Apartheid (Policy)\",\n" +
                "                \"Demonstrations, Protests and Riots\"\n" +
                "            ],\n" +
                "            \"org_facet\": [\n" +
                "                \"African National Congress\"\n" +
                "            ],\n" +
                "            \"per_facet\": [\n" +
                "                \"Zuma, Jacob G\"\n" +
                "            ],\n" +
                "            \"geo_facet\": [\n" +
                "                \"South Africa\"\n" +
                "            ],\n" +
                "            \"media\": [],\n" +
                "            \"eta_id\": 0\n" +
                "        },\n" +
                "        {\n" +
                "            \"uri\": \"nyt://article/03971d52-258c-536e-a7de-ecb7b5318ad3\",\n" +
                "            \"url\": \"https://www.nytimes.com/2021/07/13/style/ashley-olsen-photo-machete.html\",\n" +
                "            \"id\": 100000007862478,\n" +
                "            \"asset_id\": 100000007862478,\n" +
                "            \"source\": \"New York Times\",\n" +
                "            \"published_date\": \"2021-07-13\",\n" +
                "            \"updated\": \"2021-07-13 15:14:59\",\n" +
                "            \"section\": \"Style\",\n" +
                "            \"subsection\": \"\",\n" +
                "            \"nytdsection\": \"style\",\n" +
                "            \"adx_keywords\": \"your-feed-internet-culture;Olsen, Ashley;The Row (Fashion Label)\",\n" +
                "            \"column\": null,\n" +
                "            \"byline\": \"By Valeriya Safronova\",\n" +
                "            \"type\": \"Article\",\n" +
                "            \"title\": \"Ashley Olsen’s Walk in the Woods With a Machete\",\n" +
                "            \"abstract\": \"A rare photograph of a designer seldom seen or heard from has sent the internet into a tizzy.\",\n" +
                "            \"des_facet\": [\n" +
                "                \"your-feed-internet-culture\"\n" +
                "            ],\n" +
                "            \"org_facet\": [\n" +
                "                \"The Row (Fashion Label)\"\n" +
                "            ],\n" +
                "            \"per_facet\": [\n" +
                "                \"Olsen, Ashley\"\n" +
                "            ],\n" +
                "            \"geo_facet\": [],\n" +
                "            \"media\": [\n" +
                "                {\n" +
                "                    \"type\": \"image\",\n" +
                "                    \"subtype\": \"photo\",\n" +
                "                    \"caption\": \"Ashley Olsen and her twin sister, Mary-Kate, are known to be private — which is why a recent candid photo has attracted so much attention.\",\n" +
                "                    \"copyright\": \"Krista Schlueter for The New York Times\",\n" +
                "                    \"approved_for_syndication\": 1,\n" +
                "                    \"media-metadata\": [\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/13/fashion/13olsen1/13olsen1-thumbStandard.jpg\",\n" +
                "                            \"format\": \"Standard Thumbnail\",\n" +
                "                            \"height\": 75,\n" +
                "                            \"width\": 75\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/13/fashion/13olsen1/13olsen1-mediumThreeByTwo210.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo210\",\n" +
                "                            \"height\": 140,\n" +
                "                            \"width\": 210\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"https://static01.nyt.com/images/2021/07/13/fashion/13olsen1/13olsen1-mediumThreeByTwo440.jpg\",\n" +
                "                            \"format\": \"mediumThreeByTwo440\",\n" +
                "                            \"height\": 293,\n" +
                "                            \"width\": 440\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eta_id\": 0\n" +
                "        }\n" +
                "    ]\n" +
                "}"

    }

    @Test
    fun newsModelDataValidator_ReturnsTrue() {
        val gson = Gson()
        val newsData = gson.fromJson(sampleNewsDataResponse, NewsDataModel::class.java)
        assertTrue(newsData is NewsDataModel)
    }

}