import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.myassessment.openinapptask.R

class LineChartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_line_chart, container, false)

        // Initialize the LineChart
        val lineChart = view.findViewById<LineChart>(R.id.lineChart)
        setupLineChart(lineChart)

        return view
    }

    private fun setupLineChart(lineChart: LineChart) {
        // Data points for the chart
        val entries = mutableListOf<Entry>()
        entries.add(Entry(0f, 20f)) // Jan
        entries.add(Entry(1f, 50f)) // Feb
        entries.add(Entry(2f, 70f)) // Mar
        entries.add(Entry(3f, 40f)) // Apr
        entries.add(Entry(4f, 90f)) // May
        entries.add(Entry(5f, 30f)) // Jun
        entries.add(Entry(6f, 80f)) // Jul
        entries.add(Entry(7f, 75f)) // Aug

        // Creating a dataset and customizing it
        val lineDataSet = LineDataSet(entries, null)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawCircles(false)

        lineDataSet.color = Color.BLUE
        lineDataSet.lineWidth = 2f
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillDrawable = context?.let { ContextCompat.getDrawable(it, R.drawable.gradient_fill) }
        lineDataSet.fillAlpha = 50

        // Creating line data with the dataset
        val lineData = LineData(lineDataSet)
        lineChart.data = lineData

        // Customizing the X-axis
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(true)
        xAxis.valueFormatter = IndexAxisValueFormatter(arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug"))

        // Customizing the Y-axis
        val yAxis = lineChart.axisLeft
        yAxis.setDrawGridLines(true)
        yAxis.axisMinimum = 0f // Start Y-axis from 0
        yAxis.axisMaximum = 100f // Set Y-axis maximum to 100

        lineChart.axisRight.isEnabled = false // Disable right Y-axis
        lineChart.description.isEnabled = false // Disable description
        lineChart.invalidate() // Refresh the chart
    }
}