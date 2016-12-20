package similarSites;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;


public class TwoSitesSimilarity implements Writable, WritableComparable<TwoSitesSimilarity> {

    private Text site1 = new Text();
	private Text site2 = new Text();
    private Text count = new Text();


    public TwoSitesSimilarity() {
    }

    public TwoSitesSimilarity(String site1, String site2, String count) {
    	this.site1.set(site1);
    	this.site2.set(site2);
    	this.count.set(count);
    }

    public static TwoSitesSimilarity read(DataInput in) throws IOException {
    	TwoSitesSimilarity twoSitesSimilarity = new TwoSitesSimilarity();
    	twoSitesSimilarity.readFields(in);
        return twoSitesSimilarity;
    }

    public void write(DataOutput out) throws IOException {
    	site1.write(out);
    	site2.write(out);
    	count.write(out);
    }

    public void readFields(DataInput in) throws IOException {
        site1.readFields(in);
        site2.readFields(in);
        count.readFields(in);
    }

    public int compareTo(TwoSitesSimilarity twoSitesSimilarity) {
        int compareValue = twoSitesSimilarity.getSite1().compareTo(this.site1);
        if (compareValue == 0)
        	compareValue = this.count.compareTo(twoSitesSimilarity.getCount());
        return (-1)*compareValue;
    }


    public Text getSite1() {
		return site1;
	}

	public void setSite1(Text site1) {
		this.site1 = site1;
	}

	public Text getSite2() {
		return site2;
	}

	public void setSite2(Text site2) {
		this.site2 = site2;
	}

	public Text getCount() {
		return count;
	}

	public void setCount(Text count) {
		this.count = count;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwoSitesSimilarity that = (TwoSitesSimilarity) o;

        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (site1 != null ? !site1.equals(that.site1) : that.site1 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = site1 != null ? site1.hashCode() : 0;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return site1 + " with " + site2 + ": " + count;
    }
}