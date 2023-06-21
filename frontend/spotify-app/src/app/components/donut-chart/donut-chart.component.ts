import { Component, OnInit, ViewChild} from '@angular/core';
import { ChartData, ChartEvent, ChartType, ChartConfiguration } from 'chart.js';
// import DatalabelsPlugin from 'chartjs-plugin-datalabels';
import { BaseChartDirective } from 'ng2-charts';

@Component({
  selector: 'app-donut-chart',
  templateUrl: './donut-chart.component.html',
  styleUrls: ['./donut-chart.component.scss']
})
export class DonutChartComponent  {
  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

  // Pie
  public pieChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    plugins: {
      legend: {
        display: true,
        position: 'top',
      },
      // datalabels: {
      //   formatter: (value, ctx) => {
      //     if (ctx.chart.data.labels) {
      //       return ctx.chart.data.labels[ctx.dataIndex];
      //     }
      //   },
      // },
    }
  };
  public pieChartData: ChartData<'pie', number[], string | string[]> = {
    labels: [ 'Taylor Swift', 'Billie Eilish', 'Paramore' ],
    datasets: [ {
      data: [ 100, 500, 100 ]
    } ]
  };
  public pieChartType: ChartType = 'pie';
  // public pieChartPlugins = [ DatalabelsPlugin ];


}
