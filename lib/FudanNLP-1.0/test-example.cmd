@echo �ִ�ʵ��
java -classpath fudannlp-1.0.jar;lib/commons-cli-1.2.jar;lib/trove-2.1.0.jar; edu.fudan.nlp.tag.CWSTagger -s models/seg.c110722.gz "����10����˫��վ�����ݼ�����˫��վվ��֤ʵ���ݳ��ֹ��ϣ�δ�����Ա����������ԭ�򲻱�͸¶��Ŀǰ����ά�ޡ�"
@echo ���Ա�עʵ��
java -classpath fudannlp-1.0.jar;lib/commons-cli-1.2.jar;lib/trove-2.1.0.jar; edu.fudan.nlp.tag.POSTagger -s models/pos.c110722.gz "����10����˫��վ�����ݼ�����˫��վվ��֤ʵ���ݳ��ֹ��ϣ�δ�����Ա����������ԭ�򲻱�͸¶��Ŀǰ����ά�ޡ�"
@echo ʵ����ʶ��ʵ��
java -classpath fudannlp-1.0.jar;lib/commons-cli-1.2.jar;lib/trove-2.1.0.jar; edu.fudan.nlp.tag.NERTagger -s models/ner.p110722.gz "ղķ˹��Ĭ��˺�����������³��˹ ³���ء�Ĭ������µ�����С����ŦԼ�ʱ�����ְԱ����˾��ʦ��֪�������κ�Ҳ����绰��������¸�йص��ļ��� "

@pause>nul