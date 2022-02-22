# Time-Difference-App
One of the most interesting changes we made to the layout was that in LearnHard, we displayed the progress of the slider, so the 
user knew exactly how much progress he/she made. The original design did not include this, and we felt that it would be a lot
harder if user had to exactly guess the progress of the bar as well. Apart from this, not many interesting changes.

No, we didn't think it was necessary to have 6 different activities. If we would have 6 different activities, they would
really only differ marginally (in the appearance, and slightly functional sense). That is, the results screen and the guessing 
screens specifically do not need to be separate. It is far easier to make modifications to the learn screens in response to user
input (i.e. disappearing images and appearing images on button clicks) than to create intents that would start different activities.
It also reduces the "boilerplate" task of recreating elements.

To test the learn easy screen, we tested 9 different situations. Small correct: Chosen small, chosen medium, chosen large.
Similarly, choosing the three when medium and large are chosen. We didn't really manipulate the correct choice in anyway,
although we probably could have by setting the random seed, or something similar. What we did was to just keep testing until
we covered all the different cases. It actually didn't take as long as you might expect, I would say maybe 12-15 attempts to cover
everything?
