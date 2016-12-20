package similarSites;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;


public class SortGroupingComparator extends WritableComparator {


    public SortGroupingComparator() {
        super(TwoSitesSimilarity.class, true);
    }


    @Override
    public int compare(WritableComparable key1, WritableComparable key2) {
    	TwoSitesSimilarity key1Obj = (TwoSitesSimilarity) key1;
    	TwoSitesSimilarity key2Obj = (TwoSitesSimilarity) key2;
        Text siteKey1 = new Text(key1Obj.getSite1().toString());
        Text siteKey2 = new Text(key2Obj.getSite1().toString());
        return siteKey1.compareTo(siteKey2);
    }
}