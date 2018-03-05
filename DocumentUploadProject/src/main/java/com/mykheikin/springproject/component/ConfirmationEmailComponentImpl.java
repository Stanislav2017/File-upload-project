package com.mykheikin.springproject.component;
import com.mykheikin.springproject.dao.ConfirmEmailDao;
import com.mykheikin.springproject.model.ConfirmEmail;
import com.mykheikin.springproject.model.User;
import com.mykheikin.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component("confirmationEmailComponent")
public class ConfirmationEmailComponentImpl implements ConfirmationEmailComponent {

    @Value("${entity.senderEmail}")
    private String senderEmail;

    @Value("${entity.password}")
    private String password;

    @Value("${entity.message}")
    private String message;

    private JavaMailSender javaMailSender;

    private ConfirmEmailDao confirmEmailDao;

    /**
     * @param javaMailSender экземпляр для {@link ConfirmationEmailComponentImpl#javaMailSender}.
     * @param confirmEmailDao экземпляр для {@link ConfirmationEmailComponentImpl#confirmEmailDao}.
     */
    @Autowired
    public ConfirmationEmailComponentImpl(final JavaMailSender javaMailSender, final ConfirmEmailDao confirmEmailDao) {
        this.javaMailSender = javaMailSender;
        this.confirmEmailDao = confirmEmailDao;
    }

    @Override
    public void sendConfirmationByEmail(String recipientEmail) {

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientEmail);
        email.setSubject("Subject text");

        String message = String.format(this.message, recipientEmail.hashCode());
        email.setText(message);

        this.javaMailSender.send(email);
    }

    @Override
    public boolean isUserConfirmedEmail(User user) {
        Integer hashcode = user.getUsername().hashCode();
        ConfirmEmail confirmEmail = this.confirmEmailDao.findById(hashcode);
        return null == confirmEmail ? true : false;
    }
}
