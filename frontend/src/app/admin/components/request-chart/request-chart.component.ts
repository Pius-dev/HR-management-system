import { Component, Input, OnChanges } from "@angular/core";
import { Chart, registerables } from "chart.js";

Chart.register(...registerables);

@Component({
  selector: "app-request-chart",
  templateUrl: "./request-chart.component.html",
  styleUrl: "./request-chart.component.scss",
})
export class RequestChartComponent implements OnChanges {
  @Input() data: any;

  ngOnChanges() {
    this.createChart();
  }

  createChart() {
    const ctx = document.getElementById("requestChart") as HTMLCanvasElement;

    if (ctx) {
      new Chart(ctx, {
        type: "bar",
        data: {
          labels: ["Total Requests", "Successful Requests", "Failed Requests"],
          datasets: [
            {
              label: "Requests Analysis",
              data: [
                this.data?.totalRequests || 0,
                this.data?.successfulRequests || 0,
                this.data?.failedRequests || 0,
              ],
              backgroundColor: ["#36A2EB", "#4CAF50", "#F44336"],
            },
          ],
        },
        options: {
          responsive: true,
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        },
      });
    }
  }
}
