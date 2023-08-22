import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { ChartData, ChartEvent, ChartType, ChartConfiguration } from 'chart.js';

// import DatalabelsPlugin from 'chartjs-plugin-datalabels';
import { BaseChartDirective, baseColors } from 'ng2-charts';

@Component({
  selector: 'app-donut-chart',
  templateUrl: './donut-chart.component.html',
  styleUrls: ['./donut-chart.component.scss']
})
export class DonutChartComponent {

  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;
  @Input() chartData!: number[];
  @Input() chartLabels!: string[];
  @Input() chartOptions: any;

  ngOnInit(): void {
    console.log('DonutChartComponent'); // This is the name of the component
    console.log(this.chartData);
    console.log(this.chartOptions);

    this.pieChartData = {
      labels: this.chartLabels,
      datasets: [{
        data: this.chartData
      }]}
  } 

  constructor() {

  }
  // number[];
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
    
    labels: this.chartLabels,
    datasets: [ {
      data: [ 100, 500, 100 ]
    } ]
  };
  public pieChartType: ChartType = 'pie';
  // public pieChartPlugins = [ DatalabelsPlugin ];


}
