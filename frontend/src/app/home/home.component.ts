import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  fullText: string = "Welcome to MUTUAL FUND CALCULATOR X"
  displayedText: string = ""
  typingSpeed: number = 150

  ngOnInit() {
    this.startTyping()
  }

  startTyping() {
    let index = 0;
    const interval = setInterval(() => {
      if (index < this.fullText.length) {
        this.displayedText += this.fullText[index];
        index++;
      } else {
        clearInterval(interval);
      }
    }, this.typingSpeed);
  }
}
