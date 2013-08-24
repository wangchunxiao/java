@echo 分词实例
java -classpath fudannlp-1.0.jar;lib/commons-cli-1.2.jar;lib/trove-2.1.0.jar; edu.fudan.nlp.tag.CWSTagger -s models/seg.c110722.gz "北京10号线双井站扶梯梯级翘起，双井站站长证实扶梯出现故障，未造成人员伤亡，故障原因不便透露，目前正在维修。"
@echo 词性标注实例
java -classpath fudannlp-1.0.jar;lib/commons-cli-1.2.jar;lib/trove-2.1.0.jar; edu.fudan.nlp.tag.POSTagger -s models/pos.c110722.gz "北京10号线双井站扶梯梯级翘起，双井站站长证实扶梯出现故障，未造成人员伤亡，故障原因不便透露，目前正在维修。"
@echo 实体名识别实例
java -classpath fudannlp-1.0.jar;lib/commons-cli-1.2.jar;lib/trove-2.1.0.jar; edu.fudan.nlp.tag.NERTagger -s models/ner.p110722.gz "詹姆斯·默多克和丽贝卡·布鲁克斯 鲁珀特·默多克旗下的美国小报《纽约邮报》的职员被公司律师告知，保存任何也许与电话窃听及贿赂有关的文件。 "

@pause>nul