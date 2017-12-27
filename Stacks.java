package practice;

import java.util.Stack;

//import java.util.*;
public class Stacks {
  String expressions = "";
  Stack<Integer>operandStack=new Stack<>();
  Stack<Character>operatorStack=new Stack<>();
  Stacks(String s)
  {
      expressions=s;
  }
  private int readOperand(int i)
  {
      int value = this.expressions.charAt(i)-'0';
      i++;
      while((i<this.expressions.length())&&(Character.isDigit(this.expressions.charAt(i))))
      {
          value = value*10+this.expressions.charAt(i)-'0';
          i++;
      }
      operandStack.push(value);
      return i;
  }
  private void processAll()
  {
      if(this.operandStack.empty()||this.operatorStack.isEmpty())
          return;
      if(this.operandStack.size()==1)
          return;
      char c = this.operatorStack.peek();
      while(!this.operatorStack.empty()&&(c!='('))
      {
          int operand2 = this.operandStack.pop();
          int operand1 = this.operandStack.pop();
          c = this.operatorStack.pop();
          switch(c)
          {
          case '+':
              this.operandStack.push(operand1+operand2);
              break;
          case '-':
              this.operandStack.push(operand1-operand2);
              break;
          case '*':
              this.operandStack.push(operand1*operand2);
              break;
          case '/':
              this.operandStack.push(operand1/operand2);
              break;
          case '^':
              this.operandStack.push((int)Math.pow(operand1, operand2));
              break;
          case '%':
              this.operandStack.push(operand1%operand2);
              break;
              default:
                  System.out.println("Error");
          }
          if(!this.operatorStack.empty())
              c=this.operatorStack.peek();
      }
  }
  private void processMD()
  {
      if(this.operandStack.isEmpty()||this.operatorStack.isEmpty())
          return;
      if(this.operandStack.size()==1)
          return;
      char c = this.operatorStack.peek();
      while(c=='*'||c=='/'||c=='%'||c=='^')
      {
      int operand2=this.operandStack.pop();
      int operand1=this.operandStack.pop();
      switch(c)
      {
      case '*':
          this.operandStack.push(operand1*operand2);
          break;
      case '/':
          this.operandStack.push(operand1/operand2);
          break;
      case '^':
          this.operandStack.push((int)Math.pow(operand1, operand2));
          break;
      case '%':
          this.operandStack.push(operand1%operand2);
          break;
          default:
              System.out.println("Error");
      }   
      c=this.operatorStack.pop();
      }
      }
  private void process2LeftPar()
  {
      if(this.operandStack.isEmpty()||this.operatorStack.isEmpty())
          return;
      if(this.operandStack.size()==1)
          return;
      int operand2=this.operandStack.pop();
      int operand1=this.operandStack.pop();
      char c = this.operatorStack.pop();
      while(c !='(')
      {
      switch(c)
      {
      case '+':
          this.operandStack.push(operand1+operand2);
          break;
      case '-':
          this.operandStack.push(operand1-operand2);
          break;
      case '*':
          this.operandStack.push(operand1*operand2);
          break;
      case '/':
          this.operandStack.push(operand1/operand2);
          break;
      case '^':
          this.operandStack.push((int)Math.pow(operand1, operand2));
          break;
      case '%':
          this.operandStack.push(operand1%operand2);
          break;
          default:
              System.out.println("Error");
      }   
      c=this.operatorStack.pop();
      }
      }   
  private int evaluate()
  {
      // TODO Auto-generated method stub
      int i = 0;
      String exp = this.expressions;
      while(i<exp.length())
      {
          char c = exp.charAt(i);
          if(Character.isDigit(c))
          {
              i=readOperand(i);
          }
          else
          {
              switch (c)
              {
              case '+':
              case '-':
              processAll();
              this.operatorStack.push(c);
              break;
              case '*':
              case '/':
              case '%':
              processMD();
              this.operatorStack.push(c);
              break;
              case '^':
              processExponent();
              this.operatorStack.push(c);
              break;
              case '(':
              this.operatorStack.push(c);
              break;
              case ')':
              process2LeftPar();
              break;
              default:
              System.out.println("Error");
              }
              i++;
          }
      }
      processAll(); 
      // TODO Auto-generated method stub
      return this.operandStack.pop();
  }
  private void processExponent() {
      // TODO Auto-generated method stub
      if(this.operandStack.isEmpty()||this.operatorStack.isEmpty())
          return;
      if(this.operandStack.size()==1)
          return;
      char c = this.operatorStack.peek();
      while(c=='^')
      {
      int operand2=this.operandStack.pop();
      int operand1=this.operandStack.pop();
      switch(c)
      {
      case '^':
          this.operandStack.push((int)Math.pow(operand1, operand2));
          break;
      }
      c=this.operatorStack.pop();
      }

       
  }
  public static void main(String...strings) {
      // TODO Auto-generated method stub
      String s ="2^8+(54-2)";
      Stacks ec = new Stacks(s);
      //System.out.print(s+" "+ec.readOperand(s,0,ec.operandStack));
      System.out.println(s+" = "+ec.evaluate());
  }
}