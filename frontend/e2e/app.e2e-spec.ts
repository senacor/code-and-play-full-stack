import { AppPage } from './app.po';

describe('chat-app-frontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display a headline', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Chat');
  });

  it('should display the current user', () => {
    page.navigateTo();
    expect(page.getCurrentUser()).toEqual('Hello Chatter (sender@test.de)');
  });
});
